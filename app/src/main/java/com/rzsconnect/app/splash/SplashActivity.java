package com.rzsconnect.app.splash;

import android.content.Intent;
import static com.rzsconnect.app.utils.CONSTANTS.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.rzsconnect.app.MainActivity;
import com.rzsconnect.app.authentication.LoginActivity;
import com.rzsconnect.app.databinding.ActivitySplashBinding;
import com.rzsconnect.app.utils.BaseActivity;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initBinding();
        super.onCreate(savedInstanceState);
        afterSplash(4000);

    }


    private void initBinding (){
        b = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

    private boolean isFirstLaunch(){
        String name = getsSharedPreferences(KEY_NAME);
        return name == null;
    }
    private void afterSplash(int delayTime){

        new Handler(Looper.getMainLooper()).postDelayed(()->{

            if (isFirstLaunch())startActivity(new Intent(this, LoginActivity.class));
            else startActivity(new Intent(this, MainActivity.class));
            finish();

        }, delayTime);

    }



}