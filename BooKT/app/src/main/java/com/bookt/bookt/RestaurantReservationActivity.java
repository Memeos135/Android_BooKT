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
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RestaurantReservationActivity extends AppCompatActivity {

    private int seatCounter;
    private Context context;
    private NumberPicker daysP;
    private String [] day;
    private int currentDay = 0;
    private int daysInMonth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_activity);

        context = this;

        setupPickers(20);
//        Button reserveButton = findViewById(R.id.reserveButton);
//        reserveButton.setVisibility(View.INVISIBLE);
//        reserveButton.setEnabled(false);

    }

    // Seats Click Logic
    public void seatsClickHandler(View v){
        TextView textView = findViewById(R.id.resSeats);
        if("minus".equals(v.getTag())){
            if(seatCounter == 0){
                //
            }else{
                if(seatCounter == 1) {
                    seatCounter--;
                    textView.setText("x" + seatCounter);
                    textView.setTextColor(textView.getResources().getColor(R.color.light_grey));
                }else{
                    seatCounter--;
                    textView.setText("x" + seatCounter);
                    textView.setTextColor(textView.getResources().getColor(R.color.red_app));
                }
            }
        }else{
            if(seatCounter > 19){
                Toast.makeText(context, String.valueOf(seatCounter) +
                        " is the maximum number of seats you can reserve.", Toast.LENGTH_SHORT).show();
            }else {
                seatCounter++;
                textView.setText("x" + seatCounter);
                textView.setTextColor(textView.getResources().getColor(R.color.red_app));
            }
        }
    }

    // DatePicker
    public void dateHandler(View v){
        final TextView textView = findViewById(R.id.resDate);
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            datePickerDialog = new DatePickerDialog(context, R.style.MyDatePickerDialogTheme,
                    new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    if((month == Calendar.getInstance().MONTH-1) &&
                            (year == Calendar.getInstance().get(Calendar.YEAR)) &&
                            (dayOfMonth < Calendar.getInstance().get(Calendar.DAY_OF_MONTH))){

                        Toast.makeText(RestaurantReservationActivity.this, "You cannot reserve for a previous day", Toast.LENGTH_SHORT).show();
                        textView.setText("00/00/0000");
                        textView.setTextColor(getResources().getColor(R.color.light_grey));

                    }else if((month < Calendar.getInstance().MONTH-1) || (year < Calendar.getInstance().get(Calendar.YEAR))) {

                        Toast.makeText(RestaurantReservationActivity.this,
                                "You cannot reserve for the next or previous year 1", Toast.LENGTH_SHORT).show();
                        textView.setText("00/00/0000");
                        textView.setTextColor(getResources().getColor(R.color.light_grey));

                    }else if((month >= Calendar.getInstance().MONTH-1) && (year > Calendar.getInstance().get(Calendar.YEAR))) {

                        Toast.makeText(RestaurantReservationActivity.this,
                                "You cannot reserve for the next or previous year 2", Toast.LENGTH_SHORT).show();
                        textView.setText("00/00/0000");
                        textView.setTextColor(getResources().getColor(R.color.light_grey));

                    }else {

                        textView.setText(month + "/" +
                                dayOfMonth + "/" + year);
                        textView.setTextColor(getResources().getColor(R.color.red_app));

                    }

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

                String min = String.valueOf(minute);

                if(hourOfDay > 11){
                    if(hourOfDay == 12){
                        status = "PM";
                    }else{
                        status = "PM";
                        hourOfDay -= 12;
                    }
                }

                if(minute < 10){
                    min = "0"+min;
                }

                textView.setText(hourOfDay + ":" + min + ' ' + status);

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

    public void setupPickers(int seatMax){

        final String [] years = new String[1];
        years[0] = "2019";

        final String [] ampm  = new String[2];
        ampm[0] = "am";
        ampm[1] = "pm";

        final String [] months = new  String[12-Calendar.getInstance().get(Calendar.MONTH)];
        final String [] hours = new String[12];
        final String [] minutes = new String[4];
        final String [] seats = new String [seatMax];

        final int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        final Calendar mycal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        day= new String[(daysInMonth-currentDay)+1];

        for(int i =0;i<4;i++){
            minutes[i] = ""+(i*15);
        }

        for(int i = 0; i < months.length; i++){
            months[i] = ""+(currentMonth + i);
        }

        for(int i = 0; i < 12; i++){
            hours[i] = "" + (i+1);
        }

        for(int i = 0; i < day.length; i++){
            day[i] = ""+(currentDay + i);
        }

        for(int i=0;i<20;i++){
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