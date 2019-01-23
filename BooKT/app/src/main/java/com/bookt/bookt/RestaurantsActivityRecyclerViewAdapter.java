package com.bookt.bookt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.restaurants_view_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.restaurantImage.setImageResource(R.drawable.splash);
        holder.restaurantName.setText(list.get(position).getRestaurantName());
        holder.restaurantSubCategory.setText(list.get(position).getRestaurantSubCategory());
        holder.restaurantLocation.setText(list.get(position).getRestaurantLocation());
        holder.restaurantPriceRange.setText(list.get(position).getRestaurantPriceRange());
        holder.restaurantCloseOpenHour.setText(list.get(position).getRestaurantCloseHour());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView restaurantImage;
        TextView restaurantName;
        TextView restaurantSubCategory;
        TextView restaurantLocation;
        TextView restaurantPriceRange;
        TextView restaurantCloseOpenHour;

        public MyViewHolder(View itemView) {

            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantSubCategory = itemView.findViewById(R.id.restaurantSubCategory);
            restaurantLocation = itemView.findViewById(R.id.restaurantLocation);
            restaurantPriceRange = itemView.findViewById(R.id.restaurantPriceRange);
            restaurantCloseOpenHour = itemView.findViewById(R.id.restaurantOpenCloseHours);

        }
    }
}
