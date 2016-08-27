package com.example.noelia.tabacalera.workshops;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.noelia.tabacalera.R;
import com.example.noelia.tabacalera.events.EventsActivity;

import java.util.ArrayList;

/**
 * Created by Noelia on 13/08/2016.
 */
public class WorkshopsRecyclerAdapter extends RecyclerView.Adapter<WorkshopsRecyclerAdapter.RecyclerViewHolder> {

    private ArrayList<WorkshopsDataProvider> list = new ArrayList<>();
    private Context context;
    private final String type;

    public WorkshopsRecyclerAdapter(ArrayList<WorkshopsDataProvider> list, Context context, String type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutResId = 0;
        if (type.equals(WorkshopsActivity.class.getSimpleName())) {
            layoutResId = R.layout.item_workshops;
        } else if (type.equals(EventsActivity.class.getSimpleName())) {
            layoutResId = R.layout.item_events;
        }

        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(itemView, context, list);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        WorkshopsDataProvider dataProvider = list.get(position);
/*        holder.ivImage.setImageResource(dataProvider.getImage());
        holder.tvName.setText(dataProvider.getName());
        holder.tvSchedule.setText(dataProvider.getSchedule());
        holder.tvDescription.setText(dataProvider.getDescription());*/
        holder.tvName.setText(dataProvider.getName());
        holder.tvSchedule.setText(dataProvider.getSchedule());
//        holder.tvDescription.setText(dataProvider.getDescription());
        Glide.with(context)
                .load("http://tabacalera.net23.net/workshops/"+list.get(position).getIconUrl())
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivImage;
        TextView tvName, tvSchedule, tvDescription;
        ArrayList<WorkshopsDataProvider> list = new ArrayList<>();
        Context context;

        public RecyclerViewHolder(View itemLayoutView, Context context, ArrayList<WorkshopsDataProvider> list) {
            super(itemLayoutView);
            this.list = list;
            this.context = context;
            itemLayoutView.setOnClickListener(this);
            ivImage = (ImageView) itemLayoutView.findViewById(R.id.ivImage);
            tvName = (TextView) itemLayoutView.findViewById(R.id.tvName);
            tvSchedule = (TextView) itemLayoutView.findViewById(R.id.tvSchedule);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            WorkshopsDataProvider taller = this.list.get(position);
            Intent intent = new Intent(context, WorkshopsDetailActivity.class);
/*            intent.putExtra(WorkshopsDetailActivity.IMAGE, taller.getImage());*/
            intent.putExtra(WorkshopsDetailActivity.IMAGE, taller.getImageUrl());
            intent.putExtra(WorkshopsDetailActivity.NAME, taller.getName());
            intent.putExtra(WorkshopsDetailActivity.SCHEDULE, taller.getSchedule());
            intent.putExtra(WorkshopsDetailActivity.DESCRIPTION, taller.getDescription());
            this.context.startActivity(intent);
        }
    }
}


