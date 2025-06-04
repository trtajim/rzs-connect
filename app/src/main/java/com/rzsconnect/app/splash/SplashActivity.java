package com.rzsconnect.app.splash;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rzsconnect.app.R;
import com.rzsconnect.app.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initBinding();
        super.onCreate(savedInstanceState);

    }


    private void initBinding (){
        b = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
}