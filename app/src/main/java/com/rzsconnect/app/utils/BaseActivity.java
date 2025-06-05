package com.rzsconnect.app.utils;

import static com.rzsconnect.app.utils.CONSTANTS.*;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rzsconnect.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseActivity extends AppCompatActivity {

    protected String getsSharedPreferences(String keyWord){
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);

        String data = sharedPreferences.getString(keyWord, null);

        return data;


    }

    protected void editSharedPreferences(String keyWord, String value) {
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);

        SharedPreferences.Editor editor  = sharedPreferences.edit();
        editor.putString(keyWord, value);
        editor.apply();


    }

    protected String getStringFromEd(EditText editText){
        return editText.getText().toString().trim();
    }

    protected void clearEditText(EditText editText){
        editText.setText("");
        editText.clearFocus();

    }

    protected int getIntFromEd(EditText editText){
        return Integer.parseInt(editText.getText().toString().trim());
    }

    protected void toast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected void alert(String title, String message, Runnable runnable){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title).setMessage(message);
        if (runnable!=null){
            dialog.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    runnable.run();
                }
            }).setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }else {
            dialog.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }

        dialog.show();

    }


    public interface VolleyCallback {
        void onSuccess(JSONObject result);

    }
    public interface VolleyCallbackArray {
        void onSuccess(JSONArray result);

    }

    protected void jsonObjReq(String url, JSONObject jsonObject, final VolleyCallback callback) {
        startLoading();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
                endLoading();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        endLoading();
                        alert("Internet Connection Error", error.toString(), null);
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);


    }


protected void jsonArrayReq(String url, JSONArray jsonArray, final VolleyCallbackArray volleyCallbackArray){
    startLoading();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            volleyCallbackArray.onSuccess(response);
            endLoading();

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            endLoading();
            alert("Internet Connection Error", error.toString(), null);
        }
    });

    requestQueue.add(jsonArrayRequest);

}


    protected JSONObject jsonObjMaker(String... keyValuePairs) {
        JSONObject jsonObject = new JSONObject();

        if (keyValuePairs.length % 2 != 0) {
            toast("Internal error: mismatched key-value pairs.");
            return null;
        }

        for (int i = 0; i < keyValuePairs.length; i += 2) {
            String key = keyValuePairs[i];
            String value = keyValuePairs[i + 1];
            try {
                jsonObject.put(key, value);
            } catch (JSONException e) {
                Log.e("BaseActivity", "JSON build error: key=" + key, e);
                toast("Internal error occurred while preparing data.");
                return null;
            }
        }

        return jsonObject;
    }


    AlertDialog loadingAlert;

    protected void startLoading(){
        loadingAlert = new AlertDialog.Builder(this).setView(new ProgressBar(this)).setCancelable(false).show();
    }

    protected void endLoading(){
        if (loadingAlert != null && loadingAlert.isShowing()){
            loadingAlert.dismiss();
        }
    }


    protected Boolean isPassword(String password){
        return password.length() > 5 && password.length() < 19;

    }

    protected void delayTime(int time, Runnable toRun){
        new Handler(Looper.getMainLooper()).postDelayed(toRun, time);
    }



}
