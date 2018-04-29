package com.l124320.cloudprojectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;


public class adminspace extends AppCompatActivity implements View.OnClickListener{


    Button AdminAddRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminspace);

        AdminAddRoom=findViewById(R.id.buttonAddtheRoom);
        AdminAddRoom.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v==AdminAddRoom){
            Toast.makeText(adminspace.this, "error logging in", Toast.LENGTH_SHORT);

            startActivity(new Intent(getApplicationContext(), addroom.class));

        }
    }
}