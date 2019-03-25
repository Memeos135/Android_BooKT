package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class A6_RestaurantReservationActivity extends AppCompatActivity {

    private Context context;
    private NumberPicker daysP;
    private String [] day;
    private int currentDay = 0;
    private int daysInMonth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a6_reservation_activity);

        context = this;

        setupPickers(20);
    }

    // Back image handler
    public void backHandler(View v){
        onBackPressed();
    }

    public void buttonHandler(View v){
        Button button = (Button) v;
        if("cancelButton".equals(button.getTag())){
            onBackPressed();
        }else{
            context.startActivity(new Intent(context, A7_ReservationConfirmationActivity.class));
        }
    }

    public void setupPickers(int seatMax){

        final String [] years = new String[1];
        years[0] = "2019";

        final String [] ampm  = new String[2];
        ampm[0] = "am";
        ampm[1] = "pm";

        final String [] months = new  String[Calendar.getInstance().get(Calendar.MONTH)+1];
        final String [] hours = new String[12];
        final String [] minutes = new String[4];
        final String [] seats = new String [seatMax];

        // Month starts from 0, so we add 1 to it. This makes January start from 1 instead of 0.
        final int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        final Calendar mycal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Same as above procedure, otherwise we miss last day of the month.
        day= new String[(daysInMonth-currentDay)+1];

        // Populate the minutes array
        for(int i =0;i<4;i++){
            minutes[i] = ""+(i*15);
        }

        // Populate the months array, starting from the current month
        for(int i = 0; i < months.length; i++){
            months[i] = ""+(currentMonth + i);
        }

        // Populate the hours array
        for(int i = 0; i < 12; i++){
            hours[i] = "" + (i+1);
        }

        // Populate the days array, starting from the current day
        for(int i = 0; i < day.length; i++){
            day[i] = ""+(currentDay + i);
        }

        // Populate seats array, depending on seatMax
        for(int i=0;i<seatMax;i++){
            seats[i] = ""+(i+1);
        }


        NumberPicker seatsP   = findViewById(R.id.seatsPicker);
        daysP = findViewById(R.id.dayPicker);
        NumberPicker monthsP  = findViewById(R.id.monthPicker);
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

                // Months and Days Picker Logic

                if(months[newVal].equals("1") || months[newVal].equals("3") ||
                        months[newVal].equals("5") || months[newVal].equals("7") ||
                        months[newVal].equals("8") || months[newVal].equals("10") || months[newVal].equals("12")){
                    // january > march > may > july > august > october > december

                    if(months[newVal].equals(String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1))) {
                        sameMonthSetDays();
                    }else {
                        differentMonthSetDays(1);
                    }

                }
                else if(months[newVal].equals("4") || months[newVal].equals("6") ||
                        months[newVal].equals("9") || months[newVal].equals("11")){
                    // april > june > september > november

                    if(months[newVal].equals(String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1))) {
                        sameMonthSetDays();
                    }else {
                        differentMonthSetDays(2);
                    }
                }
                else {
                    // february
                    if (isLeapYear()) {
                        if(months[newVal].equals(String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1))) {
                            sameMonthSetDays();
                        }else {
                            differentMonthSetDays(3);
                        }
                    }

                    if(months[newVal].equals(String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1))) {
                        sameMonthSetDays();
                    }else {
                        differentMonthSetDays(4);
                    }
                }
            }
        });

    }

    public  boolean isLeapYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

    public void sameMonthSetDays(){

        day = null;
        day = new String[(daysInMonth-currentDay)+1];

        for(int i = 0; i < day.length; i++){
            day[i] = ""+(currentDay + i);
        }

        daysP.setMaxValue(day.length-1);
        daysP.setDisplayedValues(day);
        daysP.setMinValue(0);
    }

    public void differentMonthSetDays(int dayReduction){

        day = null;
        day = new String[31];

        for (int i = 0; i < day.length; i++) {
            day[i] = "" + (i + 1);
        }

        daysP.setDisplayedValues(day);
        daysP.setMinValue(0);
        daysP.setMaxValue(day.length - dayReduction);
    }

}