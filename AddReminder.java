package com.e.reminder;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddReminder extends AppCompatActivity {
    Button single;
    private String s = null;
    Button multiple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        single = (Button)findViewById(R.id.remindMe);
        multiple = (Button)findViewById(R.id.group);
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s="str";
                Intent intent = new Intent(AddReminder.this,AddSingle.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("People",s);
                startActivity(intent);
            }
        });
        multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s="g";
                Intent intent = new Intent(AddReminder.this,AddMultiple.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("People",s);
                startActivity(intent);
            }
        });
    }
}
