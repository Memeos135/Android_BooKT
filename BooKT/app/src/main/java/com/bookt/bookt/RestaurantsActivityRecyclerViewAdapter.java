package com.bookt.bookt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RestaurantsActivityRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantsActivityRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<RestaurantsActivityCard> list;

    public RestaurantsActivityRecyclerViewAdapter(Context context, ArrayList<RestaurantsActivityCard> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RestaurantsActivityRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
