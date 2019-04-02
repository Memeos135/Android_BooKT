package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A1_SearchViewResultsListAdapter extends ArrayAdapter<A1_SearchViewResultsSetter> {

    private List<A1_SearchViewResultsSetter> list;
    private Context context;
    private String [] x = new String[2];

    public A1_SearchViewResultsListAdapter(@NonNull Context context, @NonNull ArrayList<A1_SearchViewResultsSetter> objects) {
        super(context,  0, objects);
        this.context = context;
        list = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.a1_search_view_custom_listview, parent, false);
        }

        TextView restaurantName = view.findViewById(R.id.searchRestName);
        TextView restaurantDistrict = view.findViewById(R.id.searchDistName);

        restaurantName.setText(list.get(position).getName());

        setupLocationInfo(list.get(position).getLocation(), restaurantDistrict);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Country").child("Saudi Arabia")
                        .child("cities").child("Jeddah").child("Cuisine").child("ids")
                        .child(list.get(position).getCuisine());

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        A2_RestaurantsActivityCard card = dataSnapshot.child(list.get(position).getKey()).getValue(A2_RestaurantsActivityCard.class);
                        context.startActivity(new Intent(context, A3_RestaurantDetailsActivity.class)
                        .putExtra("restaurant_brief", card)
                        .putExtra("location", x)
                        .putExtra("activity", "A1")
                        .putExtra("cuisine", list.get(position).getCuisine()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        return view;
    }

    public void setupLocationInfo(String location, TextView restaurantLocation) {

        // INSTEAD OF t, WE USE card OBJECT to get LOCATION and PARSE it.
        Pattern p = Pattern.compile("@(.*),(.*),");
        Matcher m = p.matcher(location);
        if (m.find()) {
            x[0] = m.toString();
            String y = x[0].substring(x[0].indexOf("h=@") + 3, x[0].length() - 2);

            String latitude = y.substring(0, y.indexOf(","));
            String longitude = y.substring(y.indexOf(",") + 1);

            x[0] = latitude;
            x[1] = longitude;

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());

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