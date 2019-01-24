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
        // Instantiate Custom Card View
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
        holder.restaurantPriceDollarOne.setImageResource(R.drawable.common_full_open_on_phone);
        holder.restaurantPriceDollarTwo.setImageResource(R.drawable.common_full_open_on_phone);
        holder.restaurantPriceDollarThree.setImageResource(R.drawable.common_full_open_on_phone);
        holder.restaurantPriceDollarFour.setImageResource(R.drawable.common_full_open_on_phone);

        if(list.get(position).getRestaurantPriceRange()==1){

            holder.restaurantPriceDollarTwo.setVisibility(View.INVISIBLE);
            holder.restaurantPriceDollarThree.setVisibility(View.INVISIBLE);
            holder.restaurantPriceDollarFour.setVisibility(View.INVISIBLE);

        }else if(list.get(position).getRestaurantPriceRange()==2){

            holder.restaurantPriceDollarThree.setVisibility(View.INVISIBLE);
            holder.restaurantPriceDollarFour.setVisibility(View.INVISIBLE);

        }else if(list.get(position).getRestaurantPriceRange()==3){

            holder.restaurantPriceDollarThree.setVisibility(View.INVISIBLE);
            holder.restaurantPriceDollarFour.setVisibility(View.INVISIBLE);

        }

        holder.restaurantOpenHour.setText("Open - " + list.get(position).getRestaurantOpenHour() + " 00pm");
        holder.restaurantCloseHour.setText("Close - " + list.get(position).getRestaurantCloseHour() + " 00pm");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Set views from Resource folder to their corresponding IDs
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView restaurantImage;

        TextView restaurantName;
        TextView restaurantSubCategory;
        TextView restaurantLocation;

        ImageView restaurantPriceDollarOne;
        ImageView restaurantPriceDollarTwo;
        ImageView restaurantPriceDollarThree;
        ImageView restaurantPriceDollarFour;

        TextView restaurantOpenHour;
        TextView restaurantCloseHour;

        public MyViewHolder(View itemView) {

            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantSubCategory = itemView.findViewById(R.id.restaurantSubCategory);
            restaurantLocation = itemView.findViewById(R.id.restaurantLocation);

            restaurantPriceDollarOne = itemView.findViewById(R.id.imageView7);
            restaurantPriceDollarTwo = itemView.findViewById(R.id.imageView8);
            restaurantPriceDollarThree = itemView.findViewById(R.id.imageView9);
            restaurantPriceDollarFour = itemView.findViewById(R.id.imageView10);

            restaurantOpenHour = itemView.findViewById(R.id.restaurantOpenHour);
            restaurantCloseHour = itemView.findViewById(R.id.restaurantCloseHour);

        }
    }
}
