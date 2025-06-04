package com.rzsconnect.app.utils;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.rzsconnect.app.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected String getsSharedPreferences(String keyWord){
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(getString(R.string.sharedPreferences), MODE_PRIVATE);

        String data = sharedPreferences.getString(keyWord, null);

        return data;


    }

    protected void editSharedPreferences(String keyWord, String value) {
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(getString(R.string.sharedPreferences), MODE_PRIVATE);

        SharedPreferences.Editor editor  = sharedPreferences.edit();
        editor.putString(keyWord, value);
        editor.apply();


    }


}
