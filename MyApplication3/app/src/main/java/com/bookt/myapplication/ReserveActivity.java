package com.bookt.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import java.util.Calendar;

public class ReserveActivity extends AppCompatActivity {


    NumberPicker daysP;
    String [] day;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        context = this;

        String [] years = new String[1];
        years[0] = "2019";
        String [] ampm  = new String[2];
        ampm[0] = "am";
        ampm[1] = "pm";
        final String [] months = new  String[12];
        String [] hours = new String[12];
        String [] minutes = new String[60];
        day= new String[31];
        String [] seats = new String [20];

        for(int i =0;i<60;i++){
            minutes[i] = ""+i;
        }
        for(int i=1;i<13;i++){
            months[i-1] = ""+i;
            hours[i-1] = ""+i;
        }
        for(int i=1;i<32;i++){
            day[i-1] = ""+i;
        }

        for(int i=1;i<21;i++){
            seats[i-1] = ""+i;
        }



        NumberPicker seatsP   = findViewById(R.id.seatsPicker);
                     daysP    = findViewById(R.id.dayPicker);
        final NumberPicker monthsP  = findViewById(R.id.monthPicker);
        NumberPicker yearsP   = findViewById(R.id.yearPicker);
        NumberPicker hoursP   = findViewById(R.id.hoursPicker);
        NumberPicker minutesP = findViewById(R.id.minutesPicker);
        NumberPicker ampmP    = findViewById(R.id.ampmPicker);



        seatsP.setDisplayedValues(seats);
        seatsP.setMinValue(0);
        seatsP.setMaxValue(seats.length-1);
        monthsP.setDisplayedValues(months);
        monthsP.setMinValue(0);
        monthsP.setMaxValue(months.length-1);
        yearsP.setDisplayedValues(years);
        yearsP.setMinValue(0);
        yearsP.setMaxValue(years.length-1);
        hoursP.setDisplayedValues(hours);
        hoursP.setMinValue(0);
        hoursP.setMaxValue(hours.length-1);
        minutesP.setDisplayedValues(minutes);
        minutesP.setMinValue(0);
        minutesP.setMaxValue(minutes.length-1);
        daysP.setDisplayedValues(day);
        daysP.setMinValue(0);
        daysP.setMaxValue(day.length-1);
        ampmP.setDisplayedValues(ampm);
        ampmP.setMinValue(0);
        ampmP.setMaxValue(ampm.length-1);









        monthsP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(newVal == 0 || newVal == 2 || newVal == 4 || newVal == 6 || newVal == 7 || newVal == 9 || newVal ==11){
                    daysP.setMaxValue(day.length-1);

                }
                else if(newVal == 3 || newVal == 5 || newVal  == 8 || newVal == 10){
                    daysP.setMaxValue(day.length-2);
                }
                else {
                    if (isLeapYear()) {
                        daysP.setMaxValue(day.length - 3);
                    }
                    daysP.setMaxValue(day.length - 4);
                }


            }
        });


        NestedScrollView nestedScrollView = findViewById(R.id.reserve_NSV);
        nestedScrollView.setNestedScrollingEnabled(false);


        TextView reserve = findViewById(R.id.ReserveText);

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,ConfirmeActivity.class));
            }
        });












    }



    public  boolean isLeapYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }
}
