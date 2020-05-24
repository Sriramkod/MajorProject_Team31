package com.e.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.Manifest.permission.SEND_SMS;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    public static final int RequestPermissionCode = 7;
    Button add;
    Button view;
    int f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Smart Reminder");
        if(CheckingPermissionIsEnabledOrNot())
        {
            Toast.makeText(MainActivity.this, "All Permissions Granted Successfully", Toast.LENGTH_LONG).show();
      f=1;
        }

        else {

            //Calling method to enable permission.
            RequestMultiplePermission();

        }
        add = (Button)findViewById(R.id.add);
        view = (Button)findViewById(R.id.view);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddReminder.class);
                startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
        if(f==1)
            disableBroadcastReceiver(view);
    }
    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }
    public void toastMessage(String msg){
        Toast.makeText(this, " "+msg, Toast.LENGTH_SHORT).show();
    }
    private void RequestMultiplePermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        SEND_SMS
                }, RequestPermissionCode);

    }

    // Calling override method.
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {


                    boolean SendSMSPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (SendSMSPermission) {
                        Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }
    }
    public void disableBroadcastReceiver(View view){
        ComponentName receiver = new ComponentName(this, IncomingCallReceiver.class);
        PackageManager pm = this.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        Toast.makeText(this, "Now the calls will not be rejected", Toast.LENGTH_SHORT).show();
    }
    public boolean CheckingPermissionIsEnabledOrNot() {
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), SEND_SMS);

        return
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED ;

    }

    public void SaveStatus(View view) {
        Intent intent = new Intent(MainActivity.this,SaveStatus.class);
        startActivity(intent);
    }
}
