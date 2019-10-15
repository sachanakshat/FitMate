package com.example.fitmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HealthFeedAdapter extends RecyclerView.Adapter<HealthFeedAdapter.ViewHolder> {

    private List<String> hData, dData;
    private LayoutInflater mInflater;
    private View.OnClickListener mClickListener;;

    // data is passed into the constructor
    HealthFeedAdapter(Context context, List<String> head, List<String> desc) {
        this.mInflater = LayoutInflater.from(context);
        this.hData = head;
        this.dData = desc;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_health_feed, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String str1 = hData.get(position);
        String str2 = dData.get(position);
        holder.myTextView.setText(animal);
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextViewHeader;
        TextView myTextViewDesc;

        ViewHolder(View itemView) {
            super(itemView);
            myTextViewHeader = itemView.findViewById(R.id.rehf1);
            myTextViewDesc = itemView.findViewById(R.id.rehf2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

}
