package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class A0_HistoryAdapter extends ArrayAdapter<A0_ReservationsHistorySetter> {
    ArrayList<A0_ReservationsHistorySetter> list;

    public A0_HistoryAdapter(@NonNull Context context, @NonNull ArrayList<A0_ReservationsHistorySetter> objects) {
        super(context, 0,  objects);
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.a0_profile_custom_list, parent, false);
        }

        TextView restaurantName = view.findViewById(R.id.resHisTitle);
        TextView resHisDate = view.findViewById(R.id.resHisDate);
        TextView resHisTime = view.findViewById(R.id.resHisTime);

        restaurantName.setText(list.get(position).getRestaurantName());
        resHisTime.setText(list.get(position).getTime());
        resHisDate.setText(list.get(position).getDate());

        ImageView locationImage = view.findViewById(R.id.locationImage);
        locationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitude = list.get(position).getLocation().substring((list.get(position).getLocation().indexOf(",")+2));
                String longitude = list.get(position).getLocation().substring(0, list.get(position).getLocation().indexOf(",")-1);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:<" + latitude  + ">,<" + longitude + ">?q=<"
                                + latitude  + ">,<" + longitude + ">(" + list.get(position).getRestaurantName() + ")"));
                getContext().startActivity(intent);
            }
        });

        TextView editText = view.findViewById(R.id.resHisEdit);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), A6_RestaurantReservationActivity.class));
            }
        });
        return view;
    }
}
