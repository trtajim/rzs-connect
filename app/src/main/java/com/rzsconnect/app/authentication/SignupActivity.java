package com.rzsconnect.app.authentication;

import android.content.Intent;
import static com.rzsconnect.app.utils.CONSTANTS.*;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.rzsconnect.app.R;
import com.rzsconnect.app.databinding.ActivitySignupBinding;
import com.rzsconnect.app.utils.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends BaseActivity {

    private ActivitySignupBinding b;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        spinnerHandleUi();
        setupOnClicks();

    }


    private void initBinding() {
        b = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }


    private void setupOnClicks() {

        b.tvLogin.setOnClickListener(v -> {startActivity(new Intent(SignupActivity.this, LoginActivity.class));});
        b.ivBack.setOnClickListener(v -> {super.onBackPressed();});

        b.btnSubmit.setOnClickListener(v -> {handleSubmit();});
    }


    private void handleSubmit(){


        if (isValid()) {
            sendSignupRequest(

                    getStringFromEd(b.edName),
                    getStringFromEd(b.edPassword),
                    getStringFromEd(b.ednumber),
                    getStringFromEd(b.edStudentId),
                    getStringFromEd(b.edRoll),
                    b.spinnerShift.getSelectedItem().toString(),
                    b.spinnerClass.getSelectedItem().toString(),
                    b.spinnerSection.getSelectedItem().toString());
        }

    }

    private Boolean isValid(){

        String name, number, studentId, roll, shift, sClass, sSection, sPassword;
        int iNumber, iStudentId, iRoll;

        name = getStringFromEd(b.edName);
        number = getStringFromEd(b.ednumber);
        studentId = getStringFromEd(b.edStudentId);
        sPassword = getStringFromEd(b.edPassword);
        roll = getStringFromEd(b.edRoll);
        shift = b.spinnerShift.getSelectedItem().toString();
        sClass = b.spinnerClass.getSelectedItem().toString();
        sSection = b.spinnerSection.getSelectedItem().toString();


        if (sPassword.isEmpty() || name.isEmpty() || number.isEmpty() || studentId.isEmpty() || roll.isEmpty() || shift.equals(DEFAULT_SHIFT) || sClass.equals(DEFAULT_CLASS) || sSection.equals(DEFAULT_SECTION)) {
            toast("Fill all the Blanks");
            return false;
        }

        iRoll = getIntFromEd(b.edRoll);

        if (name.length() < 5 || number.length() != 10 || studentId.length() != 8 || iRoll == 0 || (!isPassword(sPassword))) {
            toast("Invalid Input");
            return false;
        }

        return true;

    }
    private void sendSignupRequest(String name, String password, String number, String studentId, String roll, String shift, String sClass, String sSection) {


        JSONObject jsonObject = jsonObjMaker(
                KEY_NAME, name,
                KEY_NUMBER, number,
                KEY_STUDENT_ID, studentId,
                KEY_ROLL, roll,
                KEY_SHIFT, shift,
                KEY_CLASS, sClass,
                KEY_SECTION, sSection,
                KEY_PASSWORD, password
        );

        String url = DOMAIN+"authentication/signUp.php";

        jsonObjReq(url, jsonObject, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {

                onSuccessRequest(result);
            }
        });

    }

    private void onSuccessRequest(JSONObject jsonObject) {
        try {
            String status = jsonObject.getString("status");
            if (status.equals("success")) {

                alert("Request Received", "Your request for creating an account is pending, please come after a while", null);
            } else {
                alert("Notice ", status, null);

            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }


    private void spinnerHandleUi() {
        String[] shifts = getResources().getStringArray(R.array.shifts);
        String[] morning = getResources().getStringArray(R.array.sectionMorning);
        String[] day = getResources().getStringArray(R.array.sectionDay);

        b.spinnerShift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedShift = shifts[position];
                ArrayAdapter<String> sectionAdapter;

                if (selectedShift.equals("Shift: Morning")) {
                    sectionAdapter = new ArrayAdapter<>(SignupActivity.this, android.R.layout.simple_spinner_item, morning);
                } else if (selectedShift.equals("Shift: Day")) {
                    sectionAdapter = new ArrayAdapter<>(SignupActivity.this, android.R.layout.simple_spinner_item, day);
                } else {
                    sectionAdapter = new ArrayAdapter<>(SignupActivity.this, android.R.layout.simple_spinner_item, morning);
                }

                sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                b.spinnerSection.setAdapter(sectionAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Handle nothing selected
            }
        });
    }


}