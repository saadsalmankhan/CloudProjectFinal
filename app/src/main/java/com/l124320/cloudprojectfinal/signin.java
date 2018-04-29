package com.l124320.cloudprojectfinal;

import android.support.v7.app.AppCompatActivity;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

public class signin extends AppCompatActivity implements View.OnClickListener {

    private EditText SigninEmail;
    private EditText SigninPass;
    private Button SigninBut;
    private TextView SignupText;

    private ProgressDialog SigninProg;

    private FirebaseAuth SigninAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        SigninEmail=findViewById(R.id.signinEmail);
        SigninPass=findViewById(R.id.signinPass);
        SigninBut=findViewById(R.id.buttonSignin);
        SignupText=findViewById(R.id.textViewNewacc);

        SigninProg=new ProgressDialog(this);

        //Firebase Authetication object initialized
        SigninAuth=FirebaseAuth.getInstance();

        //If user is already logged in
        if(SigninAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(), profile.class));
        }

        SigninBut.setOnClickListener(this);
        SignupText.setOnClickListener(this);
    }

    void loginUser(){
        final String Email=SigninEmail.getText().toString().trim();
        String Password=SigninPass.getText().toString().trim();

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
        SigninProg.setMessage("Signing in");
        SigninProg.show();
        SigninAuth.signInWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        SigninProg.dismiss();
                        if(task.isSuccessful()){
                            if(Email.equals("admin@admin.com")) {
                                //Log in
                                Toast.makeText(signin.this, "logging in", Toast.LENGTH_SHORT);
                                finish();
                                startActivity(new Intent(getApplicationContext(), adminspace.class));
                            }
                            else {
                                Toast.makeText(signin.this, "logging in", Toast.LENGTH_SHORT);
                                finish();
                                startActivity(new Intent(getApplicationContext(), profile.class));
                            }
                        }
                        else{
                            Toast.makeText(signin.this, "error logging in", Toast.LENGTH_SHORT);
                        }
                    }
                });



    }

    @Override
    public void onClick(View v){
        if(v==SigninBut){
            loginUser();
        }
        else if (v==SignupText){
            //open Signup Activity here
            finish();
            startActivity(new Intent(this,signup.class));
        }
    }
}

