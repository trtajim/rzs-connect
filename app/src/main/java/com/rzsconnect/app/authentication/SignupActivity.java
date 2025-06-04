package com.rzsconnect.app.authentication;

import android.os.Bundle;
import com.rzsconnect.app.databinding.ActivitySignupBinding;
import com.rzsconnect.app.databinding.ActivitySplashBinding;
import com.rzsconnect.app.utils.BaseActivity;

public class SignupActivity extends BaseActivity {

    private ActivitySignupBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();

    }


    private void initBinding (){
        b = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }


}