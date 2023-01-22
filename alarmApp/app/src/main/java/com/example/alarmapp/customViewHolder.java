package com.example.alarmapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class customViewHolder extends RecyclerView.ViewHolder {
    public TextView textName, textTime;
    public customViewHolder(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.listText);
        textTime = itemView.findViewById(R.id.listText2);
    }
}
