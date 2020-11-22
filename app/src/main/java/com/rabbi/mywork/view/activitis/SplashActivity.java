package com.rabbi.mywork.view.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.rabbi.mywork.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            startActivity( new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }
        else {
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            finish();
        }
    }
}