package com.bookt.bookt;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class RestaurantReservationActivity extends AppCompatActivity {

    private int seatCounter;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_activity);

        context = this;

        Button reserveButton = findViewById(R.id.reserveButton);
        reserveButton.setVisibility(View.INVISIBLE);
        reserveButton.setEnabled(false);

    }

    // Seats Click Logic
    public void seatsClickHandler(View v){
        TextView textView = findViewById(R.id.resSeats);
        if("minus".equals(v.getTag())){
            if(seatCounter == 0){
                Toast.makeText(this, "Cannot reduce further", Toast.LENGTH_SHORT).show();
                textView.setTextColor(textView.getResources().getColor(R.color.white));
            }else{
                seatCounter--;
                textView.setText("x" + seatCounter);
                textView.setTextColor(textView.getResources().getColor(R.color.red_app));
            }
        }else{
            seatCounter++;
            textView.setText("x" + seatCounter);
            textView.setTextColor(textView.getResources().getColor(R.color.red_app));
        }
    }

    // DatePicker
    public void dateHandler(View v){
        final TextView textView = findViewById(R.id.resDate);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            datePickerDialog = new DatePickerDialog(context, R.style.MyDatePickerDialogTheme,
                    new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    textView.setText(month + "/" +
                            dayOfMonth + "/" + year);

                }
            }, year, month, dayOfMonth);

            datePickerDialog.show();

        }else{

            Toast.makeText(context, "Unsupported Version", Toast.LENGTH_SHORT).show();

        }
    }

    // Back image handler
    public void backHandler(View v){
        onBackPressed();
    }

    // TimePicker
    public void timeHandler(View v){

        final TextView textView = findViewById(R.id.resTime);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        new TimePickerDialog(context, R.style.MyDatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String status = "AM";

                if(hourOfDay > 11){
                    if(hourOfDay == 12){
                        status = "PM";
                    }else{
                        status = "PM";
                        hourOfDay -= 12;
                    }
                }

                textView.setText(hourOfDay + ":" + minute + ' ' + status);

            }
        }, hour, minute, false).show();
    }

    public void buttonHandler(View v){
        Button button = (Button) v;
        if("cancelButton".equals(button.getTag())){
            onBackPressed();
        }else{
            context.startActivity(new Intent(context, ReservationConfirmationActivity.class));
        }
    }

}