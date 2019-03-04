package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivityRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantsActivityRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<RestaurantsActivityCard> list;
    private int lastPosition = -1;

    public RestaurantsActivityRecyclerViewAdapter(Context context, ArrayList<RestaurantsActivityCard> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RestaurantsActivityRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Instantiate Custom Card View
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.restaurant_view_cards, parent, false);

        return new RestaurantsActivityRecyclerViewAdapter.MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RestaurantsActivityRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.restaurantImage.setImageResource(R.drawable.test_cat);
        holder.restaurantName.setText(list.get(position).getRestaurantName());
//        holder.restaurantSubCategory.setText(list.get(position).getRestaurantSubCategory());
        holder.restaurantLocation.setText(list.get(position).getRestaurantLocation());

        if(list.get(position).getRestaurantPriceRange() == 1){

            Spannable wordtoSpan = new SpannableString(holder.restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(holder.restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.restaurantPriceDollar.setText(wordtoSpan);

        }else if(list.get(position).getRestaurantPriceRange() == 2){

            Spannable wordtoSpan = new SpannableString(holder.restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(holder.restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.restaurantPriceDollar.setText(wordtoSpan);

        }else if(list.get(position).getRestaurantPriceRange() == 3){

            Spannable wordtoSpan = new SpannableString(holder.restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(holder.restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.restaurantPriceDollar.setText(wordtoSpan);

        }else{

            Spannable wordtoSpan = new SpannableString(holder.restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(holder.restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, holder.restaurantPriceDollar.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.restaurantPriceDollar.setText(wordtoSpan);

        }

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

        TextView restaurantPriceDollar;

        TextView restaurantOpenHour;
        TextView restaurantCloseHour;

        public MyViewHolder(View itemView) {

            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantSubCategory = itemView.findViewById(R.id.resCuisine);
            restaurantLocation = itemView.findViewById(R.id.restaurantLocation);

            restaurantPriceDollar = itemView.findViewById(R.id.dollars);

            restaurantOpenHour = itemView.findViewById(R.id.restaurantOpenHour);
            restaurantCloseHour = itemView.findViewById(R.id.restaurantCloseHour);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, RestaurantDetailsActivity.class));
                }
            });
        }
    }
}
