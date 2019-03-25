package com.bookt.bookt;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class A0_HistoryAdapter extends ArrayAdapter<A0_ReservationsHistorySetter> {
    private ArrayList<A0_ReservationsHistorySetter> list;
    private String status;

    public A0_HistoryAdapter(@NonNull Context context, @NonNull ArrayList<A0_ReservationsHistorySetter> objects, String status) {
        super(context, 0,  objects);
        this.list = objects;
        this.status = status;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
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

        if(status.equals("active")){

            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContext().startActivity(new Intent(getContext(), A6_RestaurantReservationActivity.class));
                }
            });

        }else{

            restaurantName.setTextColor(view.getResources().getColor(R.color.dark_gray));
            resHisDate.setTextColor(view.getResources().getColor(R.color.dark_gray));
            resHisTime.setTextColor(view.getResources().getColor(R.color.dark_gray));

            editText.setText("Review");
            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final Dialog dialog = new Dialog(getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.a0_profile_restaurant_review);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setGravity(Gravity.TOP);
                    dialog.setCancelable(true);
                    dialog.show();

                    RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);

                    ((Button) dialog.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // DO FIREBASE

                            dialog.cancel();
                        }
                    });
                }
            });

        }

        return view;
    }
}
