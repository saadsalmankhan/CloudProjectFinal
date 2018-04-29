package com.l124320.cloudprojectfinal;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity implements View.OnClickListener{

    private Button SignoutBut;

    private FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseauth=FirebaseAuth.getInstance();

        if(firebaseauth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, signin.class));
        }

        FirebaseUser User = firebaseauth.getCurrentUser();


        SignoutBut=findViewById(R.id.homeSignout);

        SignoutBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==SignoutBut){
            finish();
            firebaseauth.signOut();
            startActivity(new Intent(this, signin.class));
        }
    }
}

