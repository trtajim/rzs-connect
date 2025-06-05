package com.rzsconnect.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import static com.rzsconnect.app.utils.CONSTANTS.*;

import com.rzsconnect.app.R;
import com.rzsconnect.app.databinding.LayoutNoticeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecyclerHomeAdapter extends RecyclerView.Adapter<RecyclerHomeAdapter.viewHolderHomeAdapter> {
    Context context;
    JSONArray jsonArray;
    public RecyclerHomeAdapter(Context context, JSONArray jsonArray){
        this.context = context;
        this.jsonArray = jsonArray;
    }

    public static class viewHolderHomeAdapter extends RecyclerView.ViewHolder{

        LayoutNoticeBinding binding;
        public viewHolderHomeAdapter(LayoutNoticeBinding binding) {

            super(binding.getRoot());
            this.binding = binding;

        }
    }

    @NonNull
    @Override
    public viewHolderHomeAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutNoticeBinding binding = LayoutNoticeBinding.inflate(LayoutInflater.from(context), parent, false);

        return new viewHolderHomeAdapter(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderHomeAdapter holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            String title = jsonObject.getString(KEY_TITLE);
            String body = jsonObject.getString(KEY_BODY);
            String sender = jsonObject.getString(KEY_SENDER);
            holder.binding.titleText.setText(title);
            holder.binding.bodyText.setText(body);
            holder.binding.senderText.setText(sender);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }


}
