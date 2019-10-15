package com.example.fitmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<String> hData, dData;
    private View.OnClickListener mClickListener;

    // data is passed into the constructor
    RecyclerViewAdapter(Context context, List<String> head, List<String> desc) {
        this.hData = head;
        this.dData = desc;
    }

    // inflates the row layout from xml when needed
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.head.setText(hData.get(position));
        holder.desc.setText(dData.get(position));
    }

    @Override
    public int getItemCount() {
        return hData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView head, desc;

        ViewHolder(@NonNull View itemView){
            super(itemView);
            head = itemView.findViewById(R.id.rowHeading);
            desc = itemView.findViewById(R.id.rowText);
        }
    }
}
