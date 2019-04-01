package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A6_RestaurantReservationActivity extends AppCompatActivity {

    private Context context;
    private NumberPicker daysP;
    private String[] day;
    private int currentDay = 0;
    private int daysInMonth;
    private EditText name;
    private EditText email;
    private EditText number;
    private String openHour;
    private String closeHour;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private String open;
    private String close;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a6_reservation_activity);

        context = this;

        final RadioGroup radioGroup = findViewById(R.id.toggleRes);

        final String firebaseID = getIntent().getStringExtra("id");

        open = getIntent().getStringExtra("open-hour");
        close = getIntent().getStringExtra("close-hour");

        // AM -> PM LOGIC

        openHour = open.substring(0, open.indexOf(":"));
        closeHour = close.substring(0, close.indexOf(":"));

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants")
                .child(firebaseID)
                .child("restaurantDetails").child("sections");

        showWaiting();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    if (dataSnapshot.getValue().toString().equals("Single")) {

                        radioGroup.removeViewAt(0);
                        radioGroup.check(R.id.singles);
                        ((RadioButton) findViewById(R.id.singles)).setText(" Only Singles");

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants").child(firebaseID)
                                .child("tableListFamily").child("tables");
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String[] x = dataSnapshot.getValue().toString().split(",");
                                    int[] list = new int[x.length];

                                    for (int i = 0; i < x.length; i++) {
                                        list[i] = Integer.parseInt(x[i]);
                                    }

                                    Arrays.sort(list);

                                    setupPickers(list[list.length - 1]);
                                    mDatabase.removeEventListener(this);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    } else if (dataSnapshot.getValue().toString().equals("Family")) {

                        radioGroup.removeViewAt(1);
                        radioGroup.check(R.id.family);
                        ((RadioButton) findViewById(R.id.family)).setText("Only Family");

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants").child(firebaseID)
                                .child("tableListFamily").child("tables");
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String[] x = dataSnapshot.getValue().toString().split(",");
                                    int[] list = new int[x.length];

                                    for (int i = 0; i < x.length; i++) {
                                        list[i] = Integer.parseInt(x[i]);
                                    }

                                    Arrays.sort(list);

                                    setupPickers(list[list.length - 1]);
                                    mDatabase.removeEventListener(this);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    } else {
                        radioGroup.check(R.id.family);
                        ((RadioButton) findViewById(R.id.family)).setText("Family");
                        ((RadioButton) findViewById(R.id.singles)).setText("Singles");

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants").child(firebaseID)
                                .child("tableListFamily").child("tables");
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()) {
                                    String[] x = dataSnapshot.getValue().toString().split(",");
                                    int[] list = new int[x.length];

                                    for (int i = 0; i < x.length; i++) {
                                        list[i] = Integer.parseInt(x[i]);
                                    }

                                    Arrays.sort(list);

                                    setupPickers(list[list.length - 1]);
                                    mDatabase.removeEventListener(this);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
                cancelWaiting();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (findViewById(R.id.waitProgressBar) == null) {
                    if (i == 2131230868) {

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants").child(firebaseID)
                                .child("tableListFamily").child("tables");
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String[] x = dataSnapshot.getValue().toString().split(",");
                                    int[] list = new int[x.length];

                                    for (int i = 0; i < x.length; i++) {
                                        list[i] = Integer.parseInt(x[i]);
                                    }

                                    Arrays.sort(list);

                                    setupSeats(list[list.length - 1]);
                                    mDatabase.removeEventListener(this);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    } else {

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants").child(firebaseID)
                                .child("tableListSingle").child("tables");
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String[] x = dataSnapshot.getValue().toString().split(",");

                                    int[] list = new int[x.length];

                                    for (int i = 0; i < x.length; i++) {
                                        list[i] = Integer.parseInt(x[i]);
                                    }

                                    Arrays.sort(list);

                                    setupSeats(list[list.length - 1]);
                                    mDatabase.removeEventListener(this);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            setupCustomerInfo();
        }
    }

    // Back image handler
    public void backHandler(View v) {
        onBackPressed();
    }

    public void buttonHandler(View v) {
        name = findViewById(R.id.fNameText);
        email = findViewById(R.id.emailText);
        number = findViewById(R.id.numText);

        Button button = (Button) v;

        if ("cancelButton".equals(button.getTag())) {
            onBackPressed();
        } else {
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                if (!name.getText().toString().equals("") && !email.getText().toString().equals("") && !number.getText().toString().equals("") &&
                        number.getText().toString().length() == 10) {
                    if (validate(email.getText().toString()) && number.getText().toString().startsWith("05")) {
                        context.startActivity(new Intent(context, A7_ReservationConfirmationActivity.class));
                    } else {
                        Toast.makeText(context, "You must provide a valid email address. Mobile numbers start with 05.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Please fill your name, number, and email address. Mobile number length is 10 digits.", Toast.LENGTH_SHORT).show();
                }
            } else {
                // PROCESS RESERVATION BACKEND
                Toast.makeText(context, "IT WORKS", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setupPickers(int seatMax) {

        final String[] years = new String[1];
        years[0] = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

//        final String [] ampm  = new String[2];
//        ampm[0] = "am";
//        ampm[1] = "pm";

        final String[] months = new String[Calendar.getInstance().get(Calendar.MONTH)];
//        final String [] minutes = new String[4];
        final String[] seats = new String[seatMax];

        // Month starts from 0, so we add 1 to it. This makes January start from 1 instead of 0.
        final int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        final Calendar mycal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Same as above procedure, otherwise we miss last day of the month.
        day = new String[(daysInMonth - currentDay) + 1];

        // Populate the minutes array
//        for(int i =0;i<4;i++){
//            minutes[i] = ""+(i*15);
//        }

        // Populate the months array, starting from the current month
        for (int i = 0; i < months.length; i++) {
            months[i] = "" + (currentMonth + i);
        }

        // Populate the days array, starting from the current day
        for (int i = 0; i < day.length; i++) {
            day[i] = "" + (currentDay + i);
        }

        // Populate seats array, depending on seatMax
        for (int i = 0; i < seatMax; i++) {
            seats[i] = "" + (i + 1);
        }

        NumberPicker seatsP = findViewById(R.id.seatsPicker);
        daysP = findViewById(R.id.dayPicker);
        NumberPicker monthsP = findViewById(R.id.monthPicker);
        NumberPicker yearsP = findViewById(R.id.yearPicker);
        final NumberPicker hoursP = findViewById(R.id.hoursPicker);
//        NumberPicker minutesP = findViewById(R.id.minutesPicker);
//        NumberPicker ampmP    = findViewById(R.id.ampmPicker);

        seatsP.setDisplayedValues(seats);
        seatsP.setMinValue(0);
        seatsP.setMaxValue(seats.length - 1);

        monthsP.setDisplayedValues(months);
        monthsP.setMinValue(0);
        monthsP.setMaxValue(months.length - 1);

        yearsP.setDisplayedValues(years);
        yearsP.setMinValue(0);
        yearsP.setMaxValue(years.length - 1);

//        minutesP.setDisplayedValues(minutes);
//        minutesP.setMinValue(0);
//        minutesP.setMaxValue(minutes.length-1);

        daysP.setDisplayedValues(day);
        daysP.setMinValue(0);
        daysP.setMaxValue(day.length - 1);

//        ampmP.setDisplayedValues(ampm);
//        ampmP.setMinValue(0);
//        ampmP.setMaxValue(ampm.length-1);

        monthsP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                // Months and Days Picker Logic

                if (months[newVal].equals("1") || months[newVal].equals("3") ||
                        months[newVal].equals("5") || months[newVal].equals("7") ||
                        months[newVal].equals("8") || months[newVal].equals("10") || months[newVal].equals("12")) {
                    // january > march > may > july > august > october > december

                    if (months[newVal].equals(String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1))) {
                        sameMonthSetDays();
                    } else {
                        differentMonthSetDays(1);
                    }

                } else if (months[newVal].equals("4") || months[newVal].equals("6") ||
                        months[newVal].equals("9") || months[newVal].equals("11")) {
                    // april > june > september > november

                    if (months[newVal].equals(String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1))) {
                        sameMonthSetDays();
                    } else {
                        differentMonthSetDays(2);
                    }
                } else {
                    // february
                    if (isLeapYear()) {
                        if (months[newVal].equals(String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1))) {
                            sameMonthSetDays();
                        } else {
                            differentMonthSetDays(3);
                        }
                    }

                    if (months[newVal].equals(String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1))) {
                        sameMonthSetDays();
                    } else {
                        differentMonthSetDays(4);
                    }
                }
            }
        });

        setupTimeForSameDay(hoursP);

        daysP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                if (i1 == 0) {
                    setupTimeForSameDay(hoursP);
                } else {
                    setupTimeForDifferentDay(hoursP);
                }
            }
        });

    }

    public boolean isLeapYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

    public void sameMonthSetDays() {

        day = null;
        day = new String[(daysInMonth - currentDay) + 1];

        for (int i = 0; i < day.length; i++) {
            day[i] = "" + (currentDay + i);
        }

        daysP.setMaxValue(day.length - 1);
        daysP.setDisplayedValues(day);
        daysP.setMinValue(0);
    }

    public void differentMonthSetDays(int dayReduction) {

        day = null;
        day = new String[31];

        for (int i = 0; i < day.length; i++) {
            day[i] = "" + (i + 1);
        }

        daysP.setDisplayedValues(day);
        daysP.setMinValue(0);
        daysP.setMaxValue(day.length - dayReduction);
    }

    public void setupCustomerInfo() {
        name = findViewById(R.id.fNameText);
        email = findViewById(R.id.emailText);
        number = findViewById(R.id.numText);

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profile_info");

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    name.setText(dataSnapshot.child("name").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    number.setText(dataSnapshot.child("mobile").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void showWaiting() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = (ViewGroup) findViewById(R.id.constraintLayout2);
        inflater.inflate(R.layout.waiting_animation, parent);
    }

    public void cancelWaiting() {
        ProgressBar progressBar = findViewById(R.id.waitProgressBar);
        ((ViewGroup) progressBar.getParent()).removeView(progressBar);
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }


    public void setupTimeForDifferentDay(NumberPicker hoursP){
        String[] newList;
        try {
            newList = new String[Integer.parseInt(closeHour) - Integer.parseInt(openHour)];

            String start = "AM";

            if (Integer.parseInt(openHour) > 12) {
                start = "PM";
            }

            // Populate the hours array
            for (int i = 0; i < newList.length; i++) {

                newList[i] = "" + (i + Integer.parseInt(openHour));

                if (Integer.parseInt(newList[i]) < 12) {
                    newList[i] += " AM";
                } else {
                    newList[i] = String.valueOf(Integer.parseInt(newList[i]) - 12);
                    newList[i] += " PM";
                }

                if (newList[i].equals("12") && start.equals("AM")) {
                    newList[i] += "12 PM";
                    int c1 = 1;
                    for (int j = i + 1; j < newList.length; j++) {
                        newList[j] = "" + c1 + " PM";
                        c1++;
                    }
                    break;
                } else if (newList[i].equals("12") && start.equals("PM")) {
                    newList[i] += "12 AM";
                    int c1 = 1;
                    for (int j = i; j < newList.length; j++) {
                        newList[j] = "" + c1 + " AM";
                        c1++;
                    }
                    break;
                }
            }

            hoursP.setDisplayedValues(newList);
            hoursP.setMinValue(0);
            hoursP.setMaxValue(newList.length - 1);

        }catch (Exception e){
            Toast.makeText(context, "Could not load available times for this restaurant.", Toast.LENGTH_SHORT).show();

            hoursP.setMinValue(0);
            hoursP.setMaxValue(0);
            hoursP.setDisplayedValues(null);

            e.printStackTrace();
        }

    }

    public void setupTimeForSameDay(NumberPicker hoursP){
        String[] newList;
        try {
            newList = new String[Integer.parseInt(closeHour) - Calendar.getInstance().get(Calendar.HOUR_OF_DAY)-1];

            String start = "AM";

            if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 12) {
                start = "PM";
            }

            // Populate the hours array
            for (int i = 0; i < newList.length; i++) {

                newList[i] = "" + (i + Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);

                if (Integer.parseInt(newList[i]) < 12) {
                    newList[i] += " AM";
                } else {
                    newList[i] = String.valueOf(Integer.parseInt(newList[i]) - 12);
                    newList[i] += " PM";
                }

                if (newList[i].equals("12") && start.equals("AM")) {
                    newList[i] += "12 PM";
                    int c1 = 1;
                    for (int j = i + 1; j < newList.length; j++) {
                        newList[j] = "" + c1 + " PM";
                        c1++;
                    }
                    break;
                } else if (newList[i].equals("12") && start.equals("PM")) {
                    newList[i] += "12 AM";
                    int c1 = 1;
                    for (int j = i; j < newList.length; j++) {
                        newList[j] = "" + c1 + " AM";
                        c1++;
                    }
                    break;
                }
            }

            hoursP.setMinValue(0);

            if (newList.length == 1) {
                hoursP.setMaxValue(0);
                String[] x = {"-"};
                hoursP.setDisplayedValues(x);
            } else {
                hoursP.setMaxValue(newList.length - 1);
                hoursP.setDisplayedValues(newList);
            }
        }catch (Exception e){
            Toast.makeText(context, "Could not load available times for this restaurant.", Toast.LENGTH_SHORT).show();

            hoursP.setMinValue(0);
            hoursP.setMaxValue(0);
            hoursP.setDisplayedValues(null);

        }
    }

    public void setupSeats(int seat){

        final String[] seats = new String[seat];
        NumberPicker seatsP = findViewById(R.id.seatsPicker);

        seatsP.setMinValue(0);
        seatsP.setMaxValue(0);
        seatsP.setDisplayedValues(null);

        for (int i = 0; i < seat; i++) {
            seats[i] = "" + (i + 1);
        }

        seatsP.setMinValue(0);
        seatsP.setMaxValue(seats.length - 1);
        seatsP.setDisplayedValues(seats);

    }
}