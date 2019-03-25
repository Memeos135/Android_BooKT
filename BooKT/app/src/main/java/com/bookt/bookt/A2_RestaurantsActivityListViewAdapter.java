package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A2_RestaurantsActivityListViewAdapter extends ArrayAdapter<A2_RestaurantsActivityCard> {

    private Context context;
    private List<A2_RestaurantsActivityCard> list;
    private int lastPosition = -1;
    private String name;
    private String [] x = new String[2];

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

        final ImageView restaurantImage = view.findViewById(R.id.restaurantImage);
        TextView restaurantName = view.findViewById(R.id.restaurantName);
        TextView restaurantLocation = view.findViewById(R.id.restaurantLocation);
        TextView restaurantOpenHour = view.findViewById(R.id.textView9);
        TextView restaurantCloseHour = view.findViewById(R.id.textView10);
        TextView restaurantReviews = view.findViewById(R.id.textView11);
        TextView restaurantStatus = view.findViewById(R.id.congestionText);

        Picasso.get()
                .load(list.get(position).getRestaurant_info().getRestaurant_image())
                .error(R.drawable.icon)
                .fit()
                .centerCrop()
                .into(restaurantImage);

        restaurantName.setText(list.get(position).getRestaurant_info().getRestaurant_name());

        setupLocationInfo(list.get(position), view);

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
                .putExtra("cuisine", name)
                .putExtra("location", x));
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
            if(position > 8){
                animation.setStartOffset(8 * 50);
            }else{
                animation.setStartOffset(position * 75);
            }
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private void checkPrice(View view, int position){
        TextView restaurantPriceDollar = view.findViewById(R.id.dollars);

        switch (list.get(position).getRestaurant_info().getRestaurant_price()) {
            case "cheap": {

                Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
                wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                        0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                restaurantPriceDollar.setText(wordtoSpan);

                break;
            }
            case "semi-moderate": {

                Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
                wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                        0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                restaurantPriceDollar.setText(wordtoSpan);

                break;
            }
            case "moderate": {

                Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
                wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                        0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                restaurantPriceDollar.setText(wordtoSpan);

                break;
            }
            case "semi-expensive": {

                Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
                wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                        0, restaurantPriceDollar.getText().toString().length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                restaurantPriceDollar.setText(wordtoSpan);

                break;
            }
            default: {

                Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
                wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                        0, restaurantPriceDollar.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                restaurantPriceDollar.setText(wordtoSpan);

                break;
            }
        }
    }

    public void setupLocationInfo(A2_RestaurantsActivityCard card, View v) {

        TextView restaurantLocation = v.findViewById(R.id.restaurantLocation);
        // INSTEAD OF t, WE USE card OBJECT to get LOCATION and PARSE it.
            Pattern p = Pattern.compile("@(.*),(.*),");
            Matcher m = p.matcher(card.getRestaurant_info().getRestaurant_location());
            if (m.find()) {
                x[0] = m.toString();
                String y = x[0].substring(x[0].indexOf("h=@") + 3, x[0].length() - 2);

                String latitude = y.substring(0, y.indexOf(","));
                String longitude = y.substring(y.indexOf(",") + 1);

                x[0] = latitude;
                x[1] = longitude;

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(v.getContext(), Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(Double.parseDouble(x[0]), Double.parseDouble(x[1]), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                    String address = addresses.get(0).getSubLocality(); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//            String city = addresses.get(0).getLocality();
//            String state = addresses.get(0).getAdminArea();
//            String country = addresses.get(0).getCountryName();
//            String postalCode = addresses.get(0).getPostalCode();
//            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                    restaurantLocation.setText(address);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                restaurantLocation.setText("Location format is wrong");
            }

    }
}
