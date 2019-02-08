package com.bookt.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Restaurants> restaurantsArrayList;

    public RestaurantsAdapter(Context context, ArrayList<Restaurants> restaurantsArrayList) {
        this.context = context;
        this.restaurantsArrayList = restaurantsArrayList;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.restaurants_view_card,viewGroup,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.restaurantsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,RestaurantsDetails.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        CardView restaurantsCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantsCardView = itemView.findViewById(R.id.restaurants_cardView);
        }
    }
}
