package com.bookt.bookt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class RestaurantReservationActivity extends AppCompatActivity {
    int seatCounter = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_reservation_activity);

    }

    public void seatsClickHandler(View v){
        TextView textView = findViewById(R.id.textView24);
        if(v.getTag().equals("minus")){
            if(seatCounter == 0){
                Toast.makeText(this, "Cannot reduce further", Toast.LENGTH_SHORT).show();
            }else{
                seatCounter--;
                textView.setText(String.valueOf(seatCounter));
            }
        }else{
            seatCounter++;
            textView.setText(String.valueOf(seatCounter));
        }
    }
}
