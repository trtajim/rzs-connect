package com.rzsconnect.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import static com.rzsconnect.app.utils.CONSTANTS.*;
import com.rzsconnect.app.adapters.RecyclerHomeAdapter;
import com.rzsconnect.app.databinding.ActivityMainBinding;
import com.rzsconnect.app.utils.BaseActivity;
import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpEssentialsUi();
        handleOnClickListeners();

        try {
            setUpRecycler();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }


    private void initBinding(){
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

    private void setUpEssentialsUi(){

        b.tvName.setText(getsSharedPreferences(KEY_NAME));
        loadImage(getsSharedPreferences(KEY_PIC), b.ivIcon);
    }

    private void setUpRecycler()throws JSONException {
        JSONArray jsonArray = new JSONArray(getIntent().getStringExtra("notices"));
        RecyclerHomeAdapter recyclerHomeAdapter = new RecyclerHomeAdapter(this,  jsonArray);
        b.recyclerMain.setAdapter(recyclerHomeAdapter);
        b.recyclerMain.setLayoutManager(new LinearLayoutManager(this));
    }

    private void handleOnClickListeners(){
        b.linProfile.setOnClickListener(v->{
            startActivity(new Intent(this, ProfileActivity.class));
        });

        b.linRoutine.setOnClickListener(v->{
            startActivity(new Intent(this, RoutineActivity.class));
        });


    }
}