package com.rzsconnect.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rzsconnect.app.databinding.LayoutRoutineBinding;
import org.json.JSONArray;
import static com.rzsconnect.app.utils.CONSTANTS.*;

public class RecyclerRoutineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private JSONArray jsonArray;

    public RecyclerRoutineAdapter(Context context, JSONArray jsonArray){
        this.context = context;
        this.jsonArray = jsonArray;

    }


    public static class RoutineViewHolder extends RecyclerView.ViewHolder{
        LayoutRoutineBinding binding;

        public RoutineViewHolder(LayoutRoutineBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutRoutineBinding binding = LayoutRoutineBinding.inflate(LayoutInflater.from(context), parent, false);

        return new RoutineViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }


}
