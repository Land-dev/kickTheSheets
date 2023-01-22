package com.example.alarmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<customViewHolder> {
    private Context context;
    private List<MyModel> alarmList;

    public CustomAdapter(Context context, List<MyModel> alarmList) {
        this.context = context;
        this.alarmList = alarmList;
    }

    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new customViewHolder(LayoutInflater.from(context).inflate(R.layout.alarm_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull customViewHolder holder, int position) {
        holder.textName.setText(alarmList.get(position).getName());
        holder.textTime.setText(alarmList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }
}
