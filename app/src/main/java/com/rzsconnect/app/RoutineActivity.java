package com.rzsconnect.app;

import static com.rzsconnect.app.utils.CONSTANTS.*;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.rzsconnect.app.adapters.RecyclerRoutineAdapter;
import com.rzsconnect.app.databinding.ActivityRoutineBinding;
import com.rzsconnect.app.utils.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoutineActivity extends BaseActivity {
    private ActivityRoutineBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setupData();


    }
    private void initBinding(){
        b = ActivityRoutineBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

    private void setupData(){
        String url = DOMAIN + "utils/routine.php";

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = jsonObjMaker(
                KEY_SHIFT, getsSharedPreferences(KEY_SHIFT).trim(),
                KEY_CLASS, getsSharedPreferences(KEY_CLASS).trim(),
                KEY_SECTION, getsSharedPreferences(KEY_SECTION).trim(),
                KEY_ROLL, getsSharedPreferences(KEY_ROLL).trim()

        );
        jsonArray.put(jsonObject);
        jsonArrayReq(true, url, jsonArray, new VolleyCallbackArray() {
            @Override
            public void onSuccess(JSONArray result) {
                setupRecycler(result);

            }
        });
    }

    private void setupRecycler(JSONArray jsonArray){
        RecyclerRoutineAdapter recyclerRoutineAdapter = new RecyclerRoutineAdapter(RoutineActivity.this, jsonArray);
        b.recyclerRoutine.setAdapter(recyclerRoutineAdapter);
        b.recyclerRoutine.setLayoutManager(new LinearLayoutManager(RoutineActivity.this));

    }

}