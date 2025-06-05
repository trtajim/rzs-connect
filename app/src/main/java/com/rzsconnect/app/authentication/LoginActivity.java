package com.rzsconnect.app.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import static com.rzsconnect.app.utils.CONSTANTS.*;
import com.rzsconnect.app.MainActivity;
import com.rzsconnect.app.databinding.ActivityLoginBinding;
import com.rzsconnect.app.utils.BaseActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setupOnclickListeners();

    }
    private void initBinding (){
        b = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

    private void setupOnclickListeners(){

        b.tvSignup.setOnClickListener(v->{startActivity(new Intent(LoginActivity.this, SignupActivity.class));});
        b.ivBack.setOnClickListener(v->{super.onBackPressed();});
        b.btnSubmit.setOnClickListener(v->{handleSubmit();});
    }

    private void handleSubmit(){
        String number, pass;
        number = getStringFromEd(b.edNumber);
        pass = getStringFromEd(b.edPass);

        if (!isValid(number, pass)){
            toast("Enter data");
            return;
        }

        checkCredentials(number, pass);

    }

    private Boolean isValid(String number, String pass){
        return number.length()==10 && isPassword(pass);
    }


    private void checkCredentials(String number, String pass){

        JSONObject jsonObject = jsonObjMaker(
                KEY_NUMBER, number,
                KEY_PASSWORD, pass
        );
        String url = DOMAIN+"authentication/login.php";

        jsonObjReq(url, jsonObject, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    String status = result.getString("status");
                    if (status.equals(STATUS_SUCCESS)){

                        loginSuccess(result);

                    }else if (status.equals(STATUS_PENDING)){

                        alert("Notice", "Your request for account creation is still pending, please come back later", null);
                    }else {
                        //wrong credentials
                        alert("Invalid Credentials", "Check your number, password and try again", null);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }


    private void loginSuccess(JSONObject jsonObject) throws JSONException {
        Log.d("loginSuccessJson", jsonObject.toString());
        String name, number, password, studentId, shift, sClass, sSection, roll;

         name = jsonObject.getString(KEY_NAME);
         number = jsonObject.getString(KEY_NUMBER);
         password = jsonObject.getString(KEY_PASSWORD);
         studentId = jsonObject.getString(KEY_STUDENT_ID);
         shift = jsonObject.getString(KEY_SHIFT);
         sClass = jsonObject.getString(KEY_CLASS);
         sSection = jsonObject.getString(KEY_SECTION);
         roll = jsonObject.getString(KEY_ROLL);

         storeDataAndSendToDash(name, number, password, studentId, shift, sClass, sSection, roll);



    }


    private void storeDataAndSendToDash(String name, String number, String password, String studentId, String shift, String sClass, String sSection, String roll){

        editSharedPreferences(KEY_NAME, name);
        editSharedPreferences(KEY_NUMBER, number);
        editSharedPreferences(KEY_PASSWORD, password);
        editSharedPreferences(KEY_STUDENT_ID, studentId);
        editSharedPreferences(KEY_SHIFT, shift);
        editSharedPreferences(KEY_CLASS, sClass);
        editSharedPreferences(KEY_SECTION, sSection);
        editSharedPreferences(KEY_ROLL, roll);

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearEditText(b.edPass);
        clearEditText(b.edNumber);
    }
}