package com.bookt.bookt;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private EditText additional_info;
    private String openHour;
    private String closeHour;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private String open;
    private String close;
    private String[] newList;
    private int selectedTime;
    private String firebaseID;
    private String selectedMonth;
    private String selectedDay;
    private String section;
    private String selectedSeat;
    private A2_RestaurantsActivityCard card;
    private String max;
    private boolean reserveFlag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a6_reservation_activity);

        context = this;

        card = getIntent().getParcelableExtra("restaurant_details");

        final RadioGroup radioGroup = findViewById(R.id.toggleRes);
        additional_info = findViewById(R.id.addInfoText);

        firebaseID = getIntent().getStringExtra("id");

        open = getIntent().getStringExtra("open-hour");
        close = getIntent().getStringExtra("close-hour");

        // the following two variables are used for AM-PM LOGIC

        openHour = open.substring(0, open.indexOf(":"));
        closeHour = close.substring(0, close.indexOf(":"));

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants")
                .child(firebaseID)
                .child("restaurantDetails").child("sections");

        showWaiting();

        // Default Picker Values
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    // If section for this restaurant is Single only
                    if (dataSnapshot.getValue().toString().equals("Single")) {

                        radioGroup.removeViewAt(0);
                        radioGroup.check(R.id.singles);
                        ((RadioButton) findViewById(R.id.singles)).setText(" Only Singles");

                        section = "single";

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants").child(firebaseID)
                                .child("tableListSingle").child("tables");

                        // Read the max table counts for seat picker values
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

                                    setupPickers();
                                    setupSeats(list);
                                    mDatabase.removeEventListener(this);
                                    cancelWaiting();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        // If section for this restaurant is Family only
                    } else if (dataSnapshot.getValue().toString().equals("Family")) {

                        radioGroup.removeViewAt(1);
                        radioGroup.check(R.id.family);
                        ((RadioButton) findViewById(R.id.family)).setText("Only Family");

                        section = "family";

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants").child(firebaseID)
                                .child("tableListFamily").child("tables");

                        // Read the max table counts for seat picker values
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

                                    setupPickers();
                                    setupSeats(list);
                                    mDatabase.removeEventListener(this);
                                    cancelWaiting();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        // If section for this restaurant is Singles and Family
                    } else {
                        radioGroup.check(R.id.family);
                        ((RadioButton) findViewById(R.id.family)).setText("Family");
                        ((RadioButton) findViewById(R.id.singles)).setText("Singles");

                        section = "family";

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants").child(firebaseID)
                                .child("tableListFamily").child("tables");

                        // Read the max table counts of Family (default) for seat picker values
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

                                    setupPickers();
                                    setupSeats(list);
                                    mDatabase.removeEventListener(this);
                                    cancelWaiting();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // set listener for radio buttons
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // only perform the following if progress bar IS null (do not duplicate)
                if (findViewById(R.id.waitProgressBar) == null) {
                    // if radio button checked is FAMILY
                    if (i == 2131230868) {

                        section = "family";

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants").child(firebaseID)
                                .child("tableListFamily").child("tables");

                        // Read the max table counts for seat picker values
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

                                    setupSeats(list);
                                    mDatabase.removeEventListener(this);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        // if radio button checked is SINGLE
                    } else {

                        section = "single";

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants").child(firebaseID)
                                .child("tableListSingle").child("tables");

                        // Read the max table counts for seat picker values
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

                                    setupSeats(list);
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

        // If user is authenticated, fill up his information
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

            // check if user is NOT verified
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {

                // if so, check his input is not empty
                if (!name.getText().toString().equals("") && !email.getText().toString().equals("") && !number.getText().toString().equals("") &&
                        number.getText().toString().length() == 10) {

                    // if all good, validate his email address and mobile number
                    if (validate(email.getText().toString()) && number.getText().toString().startsWith("05")) {

                        // if all good, proceed to confirmation activity
                        context.startActivity(new Intent(context, A7_ReservationConfirmationActivity.class)
                        .putExtra("restaurant_info",getIntent().getParcelableExtra("restaurant_details"))
                        .putExtra("time",String.valueOf(selectedTime))
                        .putExtra("section", section)
                        .putExtra("seats", selectedSeat)
                        .putExtra("month", selectedMonth)
                        .putExtra("day", selectedDay)
                        .putExtra("location", getIntent().getStringExtra("location"))
                        .putExtra("cuisine", getIntent().getStringExtra("cuisine"))
                        .putExtra("name", name.getText().toString())
                        .putExtra("email", email.getText().toString())
                        .putExtra("number", number.getText().toString())
                        .putExtra("additional_info", additional_info.getText().toString()));

                        // if validation is not passed
                    } else {
                        Toast.makeText(context, "You must provide a valid email address. Mobile numbers start with 05.", Toast.LENGTH_SHORT).show();
                    }
                    // if input has empty fields
                } else {
                    Toast.makeText(context, "Please fill your name, number, and email address. Mobile number length is 10 digits.", Toast.LENGTH_SHORT).show();
                }

                // if user IS authenticated
            } else {

                if(!reserveFlag) {
                    reserveFlag = true;

                    // check selectedTime
                    if (selectedTime != 0 && selectedMonth != null && selectedDay != null) {

                        Log.i("Test", "passed initial test");

                        // check user's reservation list in the Users node in Firebase
                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("reservations").child("active").child(firebaseID);

                        // if user has a time child node for this day and this specific restaurant
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // check if the time node's value equals the selected time

                                    boolean flag = false;

                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                        if (dataSnapshot1.child("hour").getValue().toString().equals(String.valueOf(selectedTime)) ||
                                                Integer.parseInt(dataSnapshot1.child("hour").getValue().toString()) == (selectedTime - 1) ||
                                                Integer.parseInt(dataSnapshot1.child("hour").getValue().toString()) == (selectedTime + 1)) {
                                            if((dataSnapshot1.child("month").getValue().toString().equals(selectedMonth) &&
                                                    dataSnapshot1.child("day").getValue().toString().equals(selectedDay))) {
                                                Toast.makeText(context, "You cannot reserve this time slot due to your current running reservations.", Toast.LENGTH_SHORT).show();
                                                reserveFlag = false;
                                                flag = true;
                                            }
                                        }
                                    }

                                    if (!flag) {
                                        // CHECK RESERVATION NODE

                                        Log.i("Test", "passed profile test upper");

                                        final TemporaryClass temporaryClass = new TemporaryClass(name.getText().toString(), email.getText().toString(), number.getText().toString(),
                                                additional_info.getText().toString(),
                                                String.valueOf(Calendar.getInstance().get(Calendar.YEAR)),
                                                selectedMonth, selectedDay, String.valueOf(selectedTime), section);

                                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Reservation")
                                                .child(firebaseID).child("sections").child(section).child("T" + selectedSeat);

                                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {

                                                    Log.i("Test", "passed T2 test upper");

                                                    max = dataSnapshot.child("max").getValue().toString();

                                                    final DatabaseReference r = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                            .child(selectedMonth).child(selectedDay).child(String.valueOf(selectedTime));

                                                    r.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                            Log.i("Test", "passed reservation test upper");

                                                            if (dataSnapshot.exists()) {

                                                                if (dataSnapshot.getChildrenCount() == Integer.parseInt(max)) {

                                                                    Toast.makeText(A6_RestaurantReservationActivity.this, "No available tables for T4", Toast.LENGTH_SHORT).show();
                                                                    reserveFlag = false;

                                                                } else {

                                                                    r.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                                    final DatabaseReference r2 = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                                            .child(selectedMonth).child(selectedDay).child(String.valueOf(selectedTime + 1));
                                                                    r2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                                    String key = mDatabase.child(firebaseID).push().getKey();

                                                                    mDatabase.child(key).setValue(new A0_ReservationsHistorySetter(temporaryClass.getMonth() + "/" +
                                                                            temporaryClass.getDay() + "/" + temporaryClass.getYear(),
                                                                            getIntent().getStringExtra("location"), card.getRestaurant_info().getRestaurant_name()
                                                                            , temporaryClass.getName(), temporaryClass.getEmail(), temporaryClass.getMobile(), temporaryClass.getAdditional_info(),
                                                                            temporaryClass.getYear(), temporaryClass.getMonth(), temporaryClass.getDay(), temporaryClass.getHour(),
                                                                            temporaryClass.getSections()));

                                                                    confirmedDialog();

                                                                    reserveFlag = false;

                                                                }
                                                            } else {

                                                                r.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                                final DatabaseReference r2 = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                                        .child(selectedMonth).child(selectedDay).child(String.valueOf(selectedTime + 1));
                                                                r2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                                String key = mDatabase.child(firebaseID).push().getKey();

                                                                mDatabase.child(key).setValue(new A0_ReservationsHistorySetter(temporaryClass.getMonth() + "/" +
                                                                        temporaryClass.getDay() + "/" + temporaryClass.getYear(),
                                                                        getIntent().getStringExtra("location"), card.getRestaurant_info().getRestaurant_name()
                                                                        , temporaryClass.getName(), temporaryClass.getEmail(), temporaryClass.getMobile(), temporaryClass.getAdditional_info(),
                                                                        temporaryClass.getYear(), temporaryClass.getMonth(), temporaryClass.getDay(), temporaryClass.getHour(),
                                                                        temporaryClass.getSections()));

                                                                confirmedDialog();

                                                                reserveFlag = false;

                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });

                                                    // no T4 exists
                                                } else {

                                                    Toast.makeText(A6_RestaurantReservationActivity.this, "No children exist.", Toast.LENGTH_SHORT).show();
                                                    reserveFlag = false;

                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }

                                    // still good to go
                                } else {

                                    Log.i("Test", "passed profile test2");

                                    final TemporaryClass temporaryClass = new TemporaryClass(name.getText().toString(), email.getText().toString(), number.getText().toString(),
                                            additional_info.getText().toString(),
                                            String.valueOf(Calendar.getInstance().get(Calendar.YEAR)),
                                            selectedMonth, selectedDay, String.valueOf(selectedTime), section);

                                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Reservation")
                                            .child(firebaseID).child("sections").child(section).child("T" + selectedSeat);

                                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {

                                                Log.i("Test", "passed T2 test2");

                                                max = dataSnapshot.child("max").getValue().toString();

                                                final DatabaseReference r = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                        .child(selectedMonth).child(selectedDay).child(String.valueOf(selectedTime));

                                                r.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                        Log.i("Test", "passed reservation test2");

                                                        if (dataSnapshot.exists()) {

                                                            if (dataSnapshot.getChildrenCount() == Integer.parseInt(max)) {

                                                                Toast.makeText(A6_RestaurantReservationActivity.this, "No available tables for T4", Toast.LENGTH_SHORT).show();
                                                                reserveFlag = false;

                                                            } else {

                                                                r.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                                final DatabaseReference r2 = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                                        .child(selectedMonth).child(selectedDay).child(String.valueOf(selectedTime + 1));
                                                                r2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                                String key = mDatabase.child(firebaseID).push().getKey();

                                                                mDatabase.child(key).setValue(new A0_ReservationsHistorySetter(temporaryClass.getMonth() + "/" +
                                                                        temporaryClass.getDay() + "/" + temporaryClass.getYear(),
                                                                        getIntent().getStringExtra("location"), card.getRestaurant_info().getRestaurant_name()
                                                                        , temporaryClass.getName(), temporaryClass.getEmail(), temporaryClass.getMobile(), temporaryClass.getAdditional_info(),
                                                                        temporaryClass.getYear(), temporaryClass.getMonth(), temporaryClass.getDay(), temporaryClass.getHour(),
                                                                        temporaryClass.getSections()));

                                                                confirmedDialog();

                                                                reserveFlag = false;

                                                            }
                                                        } else {

                                                            r.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                            final DatabaseReference r2 = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                                    .child(selectedMonth).child(selectedDay).child(String.valueOf(selectedTime + 1));
                                                            r2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                            String key = mDatabase.child(firebaseID).push().getKey();

                                                            mDatabase.child(key).setValue(new A0_ReservationsHistorySetter(temporaryClass.getMonth() + "/" +
                                                                    temporaryClass.getDay() + "/" + temporaryClass.getYear(),
                                                                    getIntent().getStringExtra("location"), card.getRestaurant_info().getRestaurant_name()
                                                                    , temporaryClass.getName(), temporaryClass.getEmail(), temporaryClass.getMobile(), temporaryClass.getAdditional_info(),
                                                                    temporaryClass.getYear(), temporaryClass.getMonth(), temporaryClass.getDay(), temporaryClass.getHour(),
                                                                    temporaryClass.getSections()));

                                                            confirmedDialog();

                                                            reserveFlag = false;

                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });


                                                // no T4 exists
                                            } else {

                                                Toast.makeText(A6_RestaurantReservationActivity.this, "No children exist.", Toast.LENGTH_SHORT).show();
                                                reserveFlag = false;

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    } else {

                        Toast.makeText(context, "You cannot reserve this time slot due to your current running reservations.", Toast.LENGTH_SHORT).show();
                        reserveFlag = false;
                    }
                }
            }
        }
    }

    public void setupPickers() {

        final String[] years = new String[1];
        years[0] = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

//        final String [] ampm  = new String[2];
//        ampm[0] = "am";
//        ampm[1] = "pm";

        final String[] months = new String[Calendar.getInstance().get(Calendar.MONTH)];
//        final String [] minutes = new String[4];

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

        NumberPicker seatsP = findViewById(R.id.seatsPicker);
        daysP = findViewById(R.id.dayPicker);
        NumberPicker monthsP = findViewById(R.id.monthPicker);
        NumberPicker yearsP = findViewById(R.id.yearPicker);
        final NumberPicker hoursP = findViewById(R.id.hoursPicker);
//        NumberPicker minutesP = findViewById(R.id.minutesPicker);
//        NumberPicker ampmP    = findViewById(R.id.ampmPicker);

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

        selectedMonth = months[0];

        // add listener on MonthsPicker to update each month's days accordingly
        monthsP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                selectedMonth = months[newVal];

                // Months Picker Logic
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

        selectedDay = day[0];

        // set listener for daysPicker to populate restaurant open and close times
        daysP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                selectedDay = day[i1];

                if (i1 == 0) {

                    // if day selected IS today
                    setupTimeForSameDay(hoursP);

                    // if day selected is NOT today
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


    // read user information from database and set it
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


    // email validation function
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }


    // timePicker setter for NOT today
    public void setupTimeForDifferentDay(NumberPicker hoursP){
        try {

            // create an array with size based on restaurant open and close hour
            newList = new String[Integer.parseInt(closeHour) - Integer.parseInt(openHour)-1];

            for(int i = 0; i < newList.length; i++){
                newList[i] = "" + (i+Integer.parseInt(openHour));

            }
//
//            // default AM-PM option is AM
//            String start = "AM";
//
//            // check otherwise
//            if (Integer.parseInt(openHour) > 12) {
//                start = "PM";
//            }
//
//            // Populate the hours array
//            for (int i = 0; i < newList.length; i++) {
//
//                newList[i] = "" + (i + Integer.parseInt(openHour));
//
//                // if list item (hour) is less than 12, assign it and add to it AM
//                if (Integer.parseInt(newList[i]) < 12) {
//                    newList[i] += " AM";
//
//                    // if list item (hour) is greater than 12, assign it and add to it PM
//                } else {
//                    newList[i] = String.valueOf(Integer.parseInt(newList[i]) - 12);
//                    newList[i] += " PM";
//                }
//
//                // if list item (hour) is 12, invert AM to opposite, and start fresh loop from 1
//                if (newList[i].equals("12") && start.equals("AM")) {
//                    newList[i] += "12 PM";
//
//                    // this loop does the following >> 1 AM - 2AM - 3AM ------> 11 AM >> 12 PM - 1 PM - 2 PM... etc
//                    int c1 = 1;
//                    for (int j = i + 1; j < newList.length; j++) {
//                        newList[j] = "" + c1 + " PM";
//                        c1++;
//                    }
//                    break;
//
//                    // if list item (hour) is 12, invert PM to opposite, and start fresh loop from 1
//                } else if (newList[i].equals("12") && start.equals("PM")) {
//                    newList[i] += "12 AM";
//
//                    // this loop does the following >> 1 AM - 2AM - 3AM ------> 11 AM >> 12 PM - 1 PM - 2 PM... etc
//                    int c1 = 1;
//                    for (int j = i; j < newList.length; j++) {
//                        newList[j] = "" + c1 + " AM";
//                        c1++;
//                    }
//                    break;
//                }
//            }

            // on finish above, reassign displayed list and reset min/max values of hoursPicker
            hoursP.setDisplayedValues(newList);
            hoursP.setMinValue(0);
            hoursP.setMaxValue(newList.length - 1);

//            selectedTime = Integer.parseInt(newList[0].substring(0, newList[0].indexOf(" ")));
            selectedTime = Integer.parseInt(newList[0]);

            if(!hoursP.hasOnClickListeners()) {

                hoursP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                        selectedTime = Integer.parseInt(newList[newVal].substring(0, newList[newVal].indexOf(" ")));
                        selectedTime = Integer.parseInt(newList[newVal]);
                    }
                });
            }

        }catch (Exception e){

            // on Exception, reset everything in hoursPicker
            Toast.makeText(context, "Could not load available times for this restaurant.", Toast.LENGTH_SHORT).show();

            hoursP.setMinValue(0);
            hoursP.setMaxValue(0);
            hoursP.setDisplayedValues(null);

            selectedTime = 0;

            e.printStackTrace();
        }

        listenToMax();

    }

    // timePicker setter for TODAY
    public void setupTimeForSameDay(NumberPicker hoursP){
        try {
            // create an array with size based on current hour and close hour
            if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < Integer.parseInt(openHour)){

                newList = new String[Integer.parseInt(closeHour) - Integer.parseInt(openHour) -1];
                for(int i = 0; i < newList.length; i++){
                    newList[i] = "" + (i+Integer.parseInt(openHour));
                }

            }else{

                newList = new String[Integer.parseInt(closeHour) - Calendar.getInstance().get(Calendar.HOUR_OF_DAY)-1];
                for(int i = 0; i < newList.length; i++){
                    newList[i] = "" + (i+Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
                }

            }

//
//            // default AM-PM option is AM
//            String start = "AM";
//
//            // check otherwise
//            if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 12) {
//                start = "PM";
//            }
//
//            // Populate the hours array
//            for (int i = 0; i < newList.length; i++) {
//
//                // +1 here so that users do not reserve in their current time slot. e.g, if the current time is 3,
//                // the picker starts from 4.
//                newList[i] = "" + (i + Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
//
//                // if list item (hour) is less than 12, assign it and add to it AM
//                if (Integer.parseInt(newList[i]) < 12) {
//                    newList[i] += " AM";
//
//                    // if list item (hour) is greater than 12, assign it and add to it PM
//                } else {
//                    newList[i] = String.valueOf(Integer.parseInt(newList[i]) - 12);
//                    newList[i] += " PM";
//                }
//
//                // if list item (hour) is 12, invert AM to opposite, and start fresh loop from 1
//                if (newList[i].equals("12") && start.equals("AM")) {
//                    newList[i] += "12 PM";
//
//                    // this loop does the following >> 1 AM - 2AM - 3AM ------> 11 AM >> 12 PM - 1 PM - 2 PM... etc
//                    int c1 = 1;
//                    for (int j = i + 1; j < newList.length; j++) {
//                        newList[j] = "" + c1 + " PM";
//                        c1++;
//                    }
//                    break;
//
//                    // if list item (hour) is 12, invert PM to opposite, and start fresh loop from 1
//                } else if (newList[i].equals("12") && start.equals("PM")) {
//                    newList[i] += "12 AM";
//
//                    // this loop does the following >> 1 AM - 2AM - 3AM ------> 11 AM >> 12 PM - 1 PM - 2 PM... etc
//                    int c1 = 1;
//                    for (int j = i; j < newList.length; j++) {
//                        newList[j] = "" + c1 + " AM";
//                        c1++;
//                    }
//                    break;
//                }
//            }

//            selectedTime = Integer.parseInt(newList[0].substring(0, newList[0].indexOf(" ")));
            selectedTime = Integer.parseInt(newList[0]);

            // reset hoursPicker data
            hoursP.setMinValue(0);

            // if current time has passed restaurant close hour, display "-" in hoursPicker
            if (newList.length == 1) {
                hoursP.setMaxValue(0);
                String[] x = {"-"};
                hoursP.setDisplayedValues(x);
                selectedTime = 0;

                // if data is available, display it
            } else {
                hoursP.setMaxValue(newList.length - 1);
                hoursP.setDisplayedValues(newList);
            }


            if(!hoursP.hasOnClickListeners()) {

                hoursP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                        selectedTime = Integer.parseInt(newList[newVal].substring(0, newList[newVal].indexOf(" ")));
                        selectedTime = Integer.parseInt(newList[newVal]);
                    }
                });
            }

        }catch (Exception e){

            // on Exception, reset hoursPicker
            Toast.makeText(context, "Could not load available times for this restaurant.", Toast.LENGTH_SHORT).show();

            hoursP.setMinValue(0);
            hoursP.setMaxValue(0);
            hoursP.setDisplayedValues(null);

            selectedTime = 0;

        }
    }


    // seatPicker setter for this restaurant
    public void setupSeats(int [] seat){

        final String[] seats = new String[seat.length];
        NumberPicker seatsP = findViewById(R.id.seatsPicker);

        // reset previous data
        seatsP.setMinValue(0);
        seatsP.setMaxValue(0);
        seatsP.setDisplayedValues(null);

        // assign new data
        for (int i = 0; i < seat.length; i++) {
            seats[i] = "" + seat[i];
        }

        seatsP.setMinValue(0);
        seatsP.setMaxValue(seats.length - 1);
        seatsP.setDisplayedValues(seats);

        selectedSeat = seats[0];

        if(!seatsP.hasOnClickListeners()){
            seatsP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    selectedSeat = seats[newVal];
                }
            });
        }

    }

    public void confirmedDialog(){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.a7_confirmed_dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.create();
        }
        dialog.show();

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.cancel();
                startActivity(new Intent(context, A1_GalleryActivity.class));
            }
        });
    }

    public void listenToMax(){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Reservation")
                .child(firebaseID).child("sections").child(section).child("T" + selectedSeat).child("max");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                max = dataSnapshot.getValue().toString();
                Toast.makeText(context, "Max added.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                max = dataSnapshot.getValue().toString();
                Toast.makeText(context, "Max updated.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                max = String.valueOf(0);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                max = String.valueOf(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}