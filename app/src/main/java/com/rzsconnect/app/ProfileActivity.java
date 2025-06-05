package com.rzsconnect.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.rzsconnect.app.databinding.ActivityProfileBinding;
import com.rzsconnect.app.splash.SplashActivity;
import com.rzsconnect.app.utils.BaseActivity;
import static com.rzsconnect.app.utils.CONSTANTS.*;


public class ProfileActivity extends BaseActivity {

    private ActivityProfileBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        setExistingData();
        handleOnClickListeners();


    }

    private void initBinding(){
        b = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

    private void setExistingData(){
        String name, number, studentId, shift, sClass, sSection, sRoll;
        name = getsSharedPreferences(KEY_NAME);
        number = getsSharedPreferences(KEY_NUMBER);
        studentId = getsSharedPreferences(KEY_STUDENT_ID);
        shift = getsSharedPreferences(KEY_SHIFT);
        sClass = getsSharedPreferences(KEY_CLASS);
        sSection = getsSharedPreferences(KEY_SECTION);
        sRoll = getsSharedPreferences(KEY_ROLL);

        b.tvName.setText(name);
        b.tvPhone.setText("+880 "+number);
        b.tvStudentId.setText("127372 "+studentId);
        b.tvShift.setText(shift);
        b.tvClass.setText(sClass);
        b.tvSection.setText(sSection);
        b.tvRoll.setText("Roll: "+sRoll);




    }

    public void toastOnclickXML(View view){
        toast("Contact your teacher to change this information");
    }

    private void handleOnClickListeners(){
        b.btnSubmit.setOnClickListener(v->{
            String oldPassword = getStringFromEd(b.edOldPassword);
            String newPassword = getStringFromEd(b.edNewPassword);

            if (!isPassword(oldPassword) || !isPassword(newPassword)){
                toast("Enter Password");
                return;
            }

        });



        b.ivLogOut.setOnClickListener(v->{
            alert("Log out?", "By logging out, you won't be access this account in this device until login.", ()->{
                editSharedPreferences(KEY_NAME, null);

                Intent intent = new Intent(this, SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


            } );
        });
    }
}