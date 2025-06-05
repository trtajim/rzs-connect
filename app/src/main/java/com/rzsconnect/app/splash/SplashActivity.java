package com.rzsconnect.app.splash;

import android.content.Intent;
import static com.rzsconnect.app.utils.CONSTANTS.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.rzsconnect.app.MainActivity;
import com.rzsconnect.app.authentication.LoginActivity;
import com.rzsconnect.app.databinding.ActivitySplashBinding;
import com.rzsconnect.app.utils.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initBinding();
        super.onCreate(savedInstanceState);
        checkLoggedIn();



    }


    private void initBinding (){
        b = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

    private void checkLoggedIn(){
        if (isFirstLaunch()){
            delayAndSendToLogin();
        }else {
            getNoticesAndSendToDash();
        }
    }

    private boolean isFirstLaunch(){
        String name = getsSharedPreferences(KEY_NAME);
        return name == null;
    }

    private void delayAndSendToLogin(){
        delayTime(4000, ()->{
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }


    private void getNoticesAndSendToDash(){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = jsonObjMaker(
                KEY_SHIFT, getsSharedPreferences(KEY_SHIFT).trim(),
                KEY_CLASS, getsSharedPreferences(KEY_CLASS).trim(),
                KEY_SECTION, getsSharedPreferences(KEY_SECTION).trim(),
                KEY_ROLL, getsSharedPreferences(KEY_ROLL).trim()

        );
        jsonArray.put(jsonObject);

        String url = DOMAIN+"utils/notices.php";

        jsonArrayReq(url, jsonArray, new VolleyCallbackArray() {
            @Override
            public void onSuccess(JSONArray result) {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("notices", result.toString());
                startActivity(intent);
                finish();

            }
        });
    }



}