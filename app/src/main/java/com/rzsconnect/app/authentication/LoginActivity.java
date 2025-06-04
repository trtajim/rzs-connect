package com.rzsconnect.app.authentication;

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

    }
    private void initBinding (){
        b = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
}