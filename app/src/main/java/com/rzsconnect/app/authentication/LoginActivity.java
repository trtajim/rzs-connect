package com.rzsconnect.app.authentication;

import android.content.Intent;
import android.os.Bundle;

import com.rzsconnect.app.databinding.ActivityLoginBinding;
import com.rzsconnect.app.databinding.ActivitySplashBinding;
import com.rzsconnect.app.utils.BaseActivity;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        Onclicks();

    }
    private void initBinding (){
        b = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

    private void Onclicks(){

        b.tvSignup.setOnClickListener(v->{startActivity(new Intent(LoginActivity.this, SignupActivity.class));});
        b.ivBack.setOnClickListener(v->{super.onBackPressed();});
    }
}