package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
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
        view = mInflater.inflate(R.layout.restaurant_view_cards, parent, false);

        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.restaurantImage.setImageResource(R.drawable.icon);
        holder.restaurantName.setText(list.get(position).getRestaurantName());
        holder.restaurantSubCategory.setText(list.get(position).getRestaurantSubCategory());
        holder.restaurantLocation.setText(list.get(position).getRestaurantLocation());

        if(list.get(position).getRestaurantPriceRange()==1){

            holder.restaurantPriceDollarOne.setTextColor(holder.restaurantPriceDollarOne.getResources().getColor(R.color.red_app));

        }else if(list.get(position).getRestaurantPriceRange()==2){

            holder.restaurantPriceDollarOne.setTextColor(holder.restaurantPriceDollarOne.getResources().getColor(R.color.red_app));
            holder.restaurantPriceDollarTwo.setTextColor(holder.restaurantPriceDollarTwo.getResources().getColor(R.color.red_app));

        }else if(list.get(position).getRestaurantPriceRange()==3){

            holder.restaurantPriceDollarOne.setTextColor(holder.restaurantPriceDollarOne.getResources().getColor(R.color.red_app));
            holder.restaurantPriceDollarTwo.setTextColor(holder.restaurantPriceDollarTwo.getResources().getColor(R.color.red_app));
            holder.restaurantPriceDollarThree.setTextColor(holder.restaurantPriceDollarThree.getResources().getColor(R.color.red_app));

        }else if(list.get(position).getRestaurantPriceRange()==4){

            holder.restaurantPriceDollarOne.setTextColor(holder.restaurantPriceDollarOne.getResources().getColor(R.color.red_app));
            holder.restaurantPriceDollarTwo.setTextColor(holder.restaurantPriceDollarTwo.getResources().getColor(R.color.red_app));
            holder.restaurantPriceDollarThree.setTextColor(holder.restaurantPriceDollarThree.getResources().getColor(R.color.red_app));
            holder.restaurantPriceDollarFour.setTextColor(holder.restaurantPriceDollarFour.getResources().getColor(R.color.red_app));
        }

        holder.openHour.setText(list.get(position).getRestaurantOpenHour());
        holder.closeHour.setText(list.get(position).getRestaurantCloseHour());

        holder.openHour.setTextColor(holder.restaurantOpenHour.getResources().getColor(R.color.red_app));
        holder.closeHour.setTextColor(holder.restaurantCloseHour.getResources().getColor(R.color.red_app));


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

        TextView restaurantPriceDollarOne;
        TextView restaurantPriceDollarTwo;
        TextView restaurantPriceDollarThree;
        TextView restaurantPriceDollarFour;

        TextView restaurantOpenHour;
        TextView restaurantCloseHour;
        TextView openHour;
        TextView closeHour;

        public MyViewHolder(View itemView) {

            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantSubCategory = itemView.findViewById(R.id.resCuisine);
            restaurantLocation = itemView.findViewById(R.id.restaurantLocation);

            restaurantPriceDollarOne = itemView.findViewById(R.id.dollarOne);
            restaurantPriceDollarTwo = itemView.findViewById(R.id.dollarTwo);
            restaurantPriceDollarThree = itemView.findViewById(R.id.dollarThree);
            restaurantPriceDollarFour = itemView.findViewById(R.id.dollarFour);

            restaurantOpenHour = itemView.findViewById(R.id.restaurantOpenHour);
            restaurantCloseHour = itemView.findViewById(R.id.restaurantCloseHour);
            openHour = itemView.findViewById(R.id.openHour);
            closeHour = itemView.findViewById(R.id.closeHour);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, RestaurantDetailsActivity.class));
                }
            });
        }
    }
}
