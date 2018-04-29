package com.l124320.cloudprojectfinal;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity implements View.OnClickListener {


    private EditText SignupEmail;
    private EditText SignupPass;
    private Button SignupBut;
    //private TextView SignupText;

    private ProgressDialog SignupProg;

    private FirebaseAuth SignupAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SignupEmail=findViewById(R.id.signupEmail);
        SignupPass=findViewById(R.id.signupPass);
        SignupBut=findViewById(R.id.buttonSignup);
        //SignupText=findViewById(R.id.textViewNewacc);

        SignupProg=new ProgressDialog(this);

        //Firebase Authetication object initialized
        SignupAuth=FirebaseAuth.getInstance();

        SignupBut.setOnClickListener(this);
        //SignupText.setOnClickListener(this);
    }

    void addUser(){
        String Email=SignupEmail.getText().toString().trim();
        String Password=SignupPass.getText().toString().trim();

        if(TextUtils.isEmpty(Email)){
            //empty email
            Toast.makeText(this, "Please Enter email", Toast.LENGTH_SHORT);
            return;
        }
        if(TextUtils.isEmpty(Password)){
            //empty Pass
            Toast.makeText(this, "Please Enter password", Toast.LENGTH_SHORT);
            return;
        }

        //If validations are okay we will show a progress bar
        SignupProg.setMessage("Signing up");
        SignupProg.show();
        SignupAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        SignupProg.dismiss();
                        if(task.isSuccessful()){
                            //Log in
                            finish();
                            startActivity(new Intent(getApplicationContext(), profile.class));
                        }
                        else{
                            Toast.makeText(signup.this, "error making account", Toast.LENGTH_SHORT);
                        }

                    }
                });

    }

    @Override
    public void onClick(View v){
        if(v==SignupBut){
            addUser();
        }
        else{
            //some text
        }
    }
}
