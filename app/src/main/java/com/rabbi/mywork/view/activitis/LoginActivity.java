package com.rabbi.mywork.view.activitis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rabbi.mywork.R;
import com.rabbi.mywork.view.fragments.UserForgot;
import com.rabbi.mywork.view.fragments.UserRegister;

import at.markushi.ui.CircleButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email, password;
    private CircleButton signInbtn;
    private TextView tvsignup, tvforgot;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        Init();

    }

    //find all ............
    private void Init() {
        email = findViewById(R.id.et_signin_email);
        password = findViewById(R.id.et_signin_password);
        signInbtn = findViewById(R.id.signin_btn);
        tvforgot = findViewById(R.id.signin_forgot);
        tvsignup = findViewById(R.id.signintosignup);

        signInbtn.setOnClickListener(this);
        tvforgot.setOnClickListener(this);
        tvsignup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.signin_btn) {
            //sing in
            login();
        }

        if (v.getId() == R.id.signintosignup) {
            UserRegister dialog = new UserRegister();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            dialog.show(ft, UserRegister.TAG);
        }

        if (v.getId() == R.id.signin_forgot) {
            // intent forgot activity
            UserForgot dialog = new UserForgot();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            dialog.show(ft, UserForgot.TAG);
        }

    }

    private void login() {
        String uemail = email.getText().toString();
        String upassword = password.getText().toString();

        if (uemail.length() == 0) {
            email.setError("Please enter your email");
        } else if (upassword.length() == 0) {
            password.setError("Please enter your email");
        } else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            userLogin(uemail,upassword);
        }
    }

    private void userLogin(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email,password).
                addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                            loadingBar.dismiss();
                        }else {
                            Toast.makeText(LoginActivity.this,"Please check your email and password!",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                });
    }
}