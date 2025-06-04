package com.rzsconnect.app.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.rzsconnect.app.R;
import com.rzsconnect.app.databinding.ActivitySignupBinding;
import com.rzsconnect.app.utils.BaseActivity;

public class SignupActivity extends BaseActivity {

    private ActivitySignupBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        spinnerHandleUi();
        Onclicks();

    }


    private void initBinding (){
        b = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
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
                    sectionAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, morning);
                } else if (selectedShift.equals("Shift: Day")) {
                    sectionAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, day);
                } else {
                    sectionAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, morning);
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

    private void Onclicks(){

        b.tvLogin.setOnClickListener(v->{startActivity(new Intent(SignupActivity.this, LoginActivity.class));});
        b.ivBack.setOnClickListener(v->{super.onBackPressed();});
    }







}