package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class A2_RestaurantsActivityListViewAdapter extends ArrayAdapter<A2_RestaurantsActivityCard> {

    private Context context;
    private List<A2_RestaurantsActivityCard> list;
    private int lastPosition = -1;
    private String name;

    public A2_RestaurantsActivityListViewAdapter(@NonNull Context context, @NonNull ArrayList<A2_RestaurantsActivityCard> list, String name) {
        super(context, 0,  list);
        this.context = context;
        this.list = list;
        this.name = name;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.a2_restaurant_card, parent, false);
        }

        ImageView restaurantImage = view.findViewById(R.id.restaurantImage);
        TextView restaurantName = view.findViewById(R.id.restaurantName);
        TextView restaurantLocation = view.findViewById(R.id.restaurantLocation);
        TextView restaurantOpenHour = view.findViewById(R.id.textView9);
        TextView restaurantCloseHour = view.findViewById(R.id.textView10);
        TextView restaurantReviews = view.findViewById(R.id.textView11);
        TextView restaurantStatus = view.findViewById(R.id.congestionText);


        restaurantImage.setImageResource(R.drawable.test_cat);
        restaurantName.setText(list.get(position).getRestaurant_info().getRestaurant_name());
        restaurantLocation.setText(list.get(position).getRestaurant_info().getRestaurant_location());
        restaurantOpenHour.setText(list.get(position).getRestaurant_info().getRestaurant_open());
        restaurantCloseHour.setText(list.get(position).getRestaurant_info().getRestaurant_close());

        if(list.get(position).getReviews() != null) {
            restaurantReviews.setText(String.valueOf(list.get(position).getReviews().getReviewCount()));
        }else{
            restaurantReviews.setText("0");
        }
        restaurantStatus.setText(list.get(position).getRestaurant_info().getStatus());

        checkPrice(view, position);

        restaurantImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, A3_RestaurantDetailsActivity.class)
                .putExtra("restaurant_brief", list.get(position))
                .putExtra("cuisine", name));
            }
        });

        setAnimation(view, position);
        return view;
    }

    public void updateList(ArrayList<A2_RestaurantsActivityCard> list){
        this.list = list;
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setStartOffset(position * 100);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public void checkPrice(View view, int position){
        TextView restaurantPriceDollar = view.findViewById(R.id.dollars);

        if(list.get(position).getRestaurant_info().getRestaurant_price().equals("cheap")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else if(list.get(position).getRestaurant_info().getRestaurant_price().equals("semi-moderate")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else if(list.get(position).getRestaurant_info().getRestaurant_price().equals("moderate")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else if(list.get(position).getRestaurant_info().getRestaurant_price().equals("semi-expensive")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, restaurantPriceDollar.getText().toString().length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else{

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, restaurantPriceDollar.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }
    }
}
