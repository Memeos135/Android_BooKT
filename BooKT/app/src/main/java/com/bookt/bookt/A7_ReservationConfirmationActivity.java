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
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;


public class A7_ReservationConfirmationActivity extends AppCompatActivity{
    private Context context;
    private TextView restaurantName;
    private TextView restaurantLocation;
    private TextView reservationTime;
    private TextView reservationSection;
    private TextView reservationDate;
    private TextView reservationCuisine;
    private TextView reservationSeat;
    private String name;
    private String email;
    private String number;
    private String additional_info;
    private String time;
    private String section;
    private String seats;
    private String month;
    private String day;
    private String location;
    private String cuisine;
    private A2_RestaurantsActivityCard card;
    private boolean reserveFlag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a7_confirmation_activity);
        context = this;

        TextView dollars = findViewById(R.id.dollars);

        restaurantName = findViewById(R.id.restaurantName);
        restaurantLocation = findViewById(R.id.restaurantLocation);
        reservationTime = findViewById(R.id.resTime);
        reservationSection = findViewById(R.id.textView6);
        reservationDate = findViewById(R.id.resDate);
        reservationCuisine = findViewById(R.id.resCuisine);
        reservationSeat = findViewById(R.id.resSeats);

        card = getIntent().getParcelableExtra("restaurant_info");

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        number = getIntent().getStringExtra("number");
        additional_info = getIntent().getStringExtra("additional_info");

        time = getIntent().getStringExtra("time");
        section = getIntent().getStringExtra("section");
        seats = getIntent().getStringExtra("seats");
        month = getIntent().getStringExtra("month");
        day = getIntent().getStringExtra("day");
        location = getIntent().getStringExtra("location");
        cuisine = getIntent().getStringExtra("cuisine");

        restaurantName.setText(card.getRestaurant_info().getRestaurant_name());
        restaurantLocation.setText(location);
        reservationTime.setText(time);
        reservationSection.setText(section);
        reservationDate.setText(month+"/"+day+"/"+String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        reservationCuisine.setText(cuisine);
        reservationSeat.setText(seats);

        animateButtons();

        colorPrice(dollars, card);

    }

    // Back image handler
    public void backHandler(View v){
        onBackPressed();
    }

    // animation login buttons
    public void animateButtons() {
        Button loginButton = findViewById(R.id.loginButton);
        Button googleLogin = findViewById(R.id.googleLogin);
        Button facebookLogin = findViewById(R.id.facebookLogin);
        TextView textView = findViewById(R.id.textLogin);

        loginButton.animate().alpha(1.0F).setDuration(700L);
        googleLogin.animate().alpha(1.0F).setDuration(700L);
        facebookLogin.animate().alpha(1.0F).setDuration(700L);
        textView.animate().alpha(1.0F).setDuration(700L);

    }

    // Sign in Method
    public void signinMethod(View v) {
        // app signin & signup
        if ("loginSignupButton".equals(v.getTag())) {

            if(!reserveFlag) {
                reserveFlag = true;

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.login_dialog);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawableResource(R.color.app_black);
                dialog.setCancelable(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialog.create();
                }
                dialog.show();

                final EditText emails = dialog.findViewById(R.id.usernameText);
                final EditText password = dialog.findViewById(R.id.passwordText);

                emails.setText(email);

                dialog.findViewById(R.id.homeImage).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(emails.getText().toString(), password.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            handleSignedUser(dialog, emails);
                                        } else {
                                            Toast.makeText(A7_ReservationConfirmationActivity.this, "Incorrect credentials.", Toast.LENGTH_SHORT).show();
                                            reserveFlag = false;
                                        }
                                    }
                                });
                    }
                });
            }

            // google signin
        }else if("googleLogin".equals(v.getTag())){
            Button button = (Button) v;

            List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    100);

            // facebook signin
        }else if("facebookLogin".equals(v.getTag())){
            Button button = (Button) v;

            List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.FacebookBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    100);
            // no signin
        }else if("textLogin".equals(v.getTag())) {

            if (!reserveFlag) {
                reserveFlag = true;

                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    Log.i("Test", "passed profile test");

                    final TemporaryClass temporaryClass = new TemporaryClass(name, email, number,
                            additional_info,
                            String.valueOf(Calendar.getInstance().get(Calendar.YEAR)),
                            month, day, time, section);

                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Reservation")
                            .child(card.getRestaurant_info().getFirebase_id()).child("sections").child(section).child("T" + seats);

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {

                                Log.i("Test", "passed T2 test");

                                final String max = dataSnapshot.child("max").getValue().toString();

                                final DatabaseReference r = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                        .child(month).child(day).child(time);

                                r.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        Log.i("Test", "passed reservation test");

                                        if (dataSnapshot.exists()) {

                                            if (dataSnapshot.getChildrenCount() < Integer.parseInt(max)) {

                                                boolean flag = false;

                                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                                    if (dataSnapshot1.child("email").getValue().toString().equals(email)) {
                                                        flag = true;
                                                        break;
                                                    }
                                                }

                                                if (!flag) {

                                                    boolean secondFlag = false;

                                                    DatabaseReference reference1 = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                            .child(month).child(day).child(String.valueOf(Integer.parseInt(time) + 1));

                                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                                        if (dataSnapshot1.child("email").getValue().toString().equals(email)) {
                                                            secondFlag = true;
                                                            break;
                                                        }
                                                    }

                                                    if (!secondFlag) {

                                                        String key = r.push().getKey();

                                                        r.child(key).setValue(temporaryClass);

                                                        reference1.child(key).setValue(temporaryClass);

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

                                                        reserveFlag = false;

                                                    }

                                                } else {
                                                    Toast.makeText(A7_ReservationConfirmationActivity.this, "You cannot reserve twice.", Toast.LENGTH_SHORT).show();
                                                    reserveFlag = false;
                                                }
                                            } else {
                                                Toast.makeText(A7_ReservationConfirmationActivity.this, "No T" + seats + " available.", Toast.LENGTH_SHORT).show();
                                                reserveFlag = false;
                                            }
                                        } else {

                                            DatabaseReference reference1 = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                    .child(month).child(day).child(String.valueOf(Integer.parseInt(time) + 1));

                                            String key = r.push().getKey();

                                            r.child(key).setValue(temporaryClass);

                                            reference1.child(key).setValue(temporaryClass);

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

                                            reserveFlag = false;

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                                // no T4 exists
                            } else {

                                Toast.makeText(A7_ReservationConfirmationActivity.this, "No children exist.", Toast.LENGTH_SHORT).show();
                                reserveFlag = false;

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {

                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(context, "You have been signed out.", Toast.LENGTH_SHORT).show();
                    reserveFlag = false;

                }
            }
        }
    }

    public void colorPrice(TextView restaurantPriceDollar, A2_RestaurantsActivityCard card){

        Log.i("test", card.getRestaurant_info().getRestaurant_price());

        if(card.getRestaurant_info().getRestaurant_price().equals("cheap")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else if(card.getRestaurant_info().getRestaurant_price().equals("semi-moderate")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else if(card.getRestaurant_info().getRestaurant_price().equals("moderate")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else if(card.getRestaurant_info().getRestaurant_price().equals("semi-expensive")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, restaurantPriceDollar.getText().toString().length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else{

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, restaurantPriceDollar.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }
    }


    public void handleSignedUser(final Dialog dialog, final EditText email){
        // check selectedTime
        if(Integer.parseInt(time)!=0 && month != null && day != null){

            Log.i("Test", "passed initial test");

            // check user's reservation list in the Users node in Firebase
            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("reservations").child("active").child(card.getRestaurant_info().getFirebase_id());

            // if user has a time child node for this day and this specific restaurant
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        // check if the time node's value equals the selected time

                        boolean flag = false;

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            if (dataSnapshot1.child("hour").getValue().toString().equals(time) ||
                                    Integer.parseInt(dataSnapshot1.child("hour").getValue().toString()) == (Integer.parseInt(time) - 1) ||
                                    Integer.parseInt(dataSnapshot1.child("hour").getValue().toString()) == (Integer.parseInt(time) + 1)) {
                                if((dataSnapshot1.child("month").getValue().toString().equals(month) &&
                                        dataSnapshot1.child("day").getValue().toString().equals(day))) {
                                    Toast.makeText(context, "You cannot reserve this time slot due to your current running reservations.", Toast.LENGTH_SHORT).show();
                                    reserveFlag = false;
                                    flag = true;
                                }
                            }
                        }

                        if (!flag) {
                            // CHECK RESERVATION NODE

                            Log.i("Test", "passed profile test upper");

                            final TemporaryClass temporaryClass = new TemporaryClass(name, email.getText().toString(), number,
                                    additional_info,
                                    String.valueOf(Calendar.getInstance().get(Calendar.YEAR)),
                                    month, day, time, section);

                            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Reservation")
                                    .child(card.getRestaurant_info().getFirebase_id()).child("sections").child(section).child("T" + seats);

                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {

                                        Log.i("Test", "passed T2 test upper");

                                        final String max = dataSnapshot.child("max").getValue().toString();

                                        final DatabaseReference r = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                .child(month).child(day).child(time);

                                        r.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                Log.i("Test", "passed reservation test upper");

                                                if (dataSnapshot.exists()) {

                                                    if (dataSnapshot.getChildrenCount() == Integer.parseInt(max)) {

                                                        Toast.makeText(A7_ReservationConfirmationActivity.this, "No available tables for T4", Toast.LENGTH_SHORT).show();
                                                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                                        mAuth.signOut();
                                                        reserveFlag = false;

                                                    } else {

                                                        r.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                        final DatabaseReference r2 = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                                .child(month).child(day).child(String.valueOf(Integer.parseInt(time)+1));
                                                        r2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                        String key = mDatabase.child(card.getRestaurant_info().getFirebase_id()).push().getKey();

                                                        mDatabase.child(key).setValue(temporaryClass);

                                                        dialog.setContentView(R.layout.a7_confirmed_dialog);
                                                        dialog.setCancelable(true);
                                                        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                                                        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                                                        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                            @Override
                                                            public void onCancel(DialogInterface dialog) {
                                                                dialog.cancel();
                                                                startActivity(new Intent(context, A1_GalleryActivity.class));
                                                            }
                                                        });

                                                        reserveFlag = false;

                                                    }
                                                }
                                                else {

                                                    r.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                    final DatabaseReference r2 = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                            .child(month).child(day).child(String.valueOf(Integer.parseInt(time)+1));

                                                    r2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                    String key = mDatabase.child(card.getRestaurant_info().getFirebase_id()).push().getKey();

                                                    mDatabase.child(key).setValue(temporaryClass);

                                                    dialog.setContentView(R.layout.a7_confirmed_dialog);
                                                    dialog.setCancelable(true);
                                                    dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                                                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                                                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                        @Override
                                                        public void onCancel(DialogInterface dialog) {
                                                            dialog.cancel();
                                                            startActivity(new Intent(context, A1_GalleryActivity.class));
                                                        }
                                                    });

                                                    reserveFlag = false;

                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                        // no T4 exists
                                    } else {

                                        Toast.makeText(A7_ReservationConfirmationActivity.this, "No children exist.", Toast.LENGTH_SHORT).show();
                                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                        mAuth.signOut();
                                        reserveFlag = false;

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        // still good to go
                    }else{

                        Log.i("Test", "passed profile test");

                        final TemporaryClass temporaryClass = new TemporaryClass(name, email.getText().toString(), number,
                                additional_info,
                                String.valueOf(Calendar.getInstance().get(Calendar.YEAR)),
                                month, day, time, section);

                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Reservation")
                                .child(card.getRestaurant_info().getFirebase_id()).child("sections").child(section).child("T" + seats);

                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){

                                    Log.i("Test", "passed T2 test");

                                    final String max = dataSnapshot.child("max").getValue().toString();

                                    final DatabaseReference r = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                            .child(month).child(day).child(time);

                                    r.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            Log.i("Test", "passed reservation test");

                                            if(dataSnapshot.exists()){

                                                if(dataSnapshot.getChildrenCount() == Integer.parseInt(max)){

                                                    Toast.makeText(A7_ReservationConfirmationActivity.this, "No available tables for T4", Toast.LENGTH_SHORT).show();
                                                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                                    mAuth.signOut();
                                                    reserveFlag = false;

                                                }else{

                                                    r.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                    final DatabaseReference r2 = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                            .child(month).child(day).child(String.valueOf(Integer.parseInt(time)+1));
                                                    r2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                    String key = mDatabase.child(card.getRestaurant_info().getFirebase_id()).push().getKey();

                                                    mDatabase.child(key).setValue(temporaryClass);

                                                    dialog.setContentView(R.layout.a7_confirmed_dialog);
                                                    dialog.setCancelable(true);
                                                    dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                                                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                                                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                        @Override
                                                        public void onCancel(DialogInterface dialog) {
                                                            dialog.cancel();
                                                            startActivity(new Intent(context, A1_GalleryActivity.class));
                                                        }
                                                    });

                                                    reserveFlag = false;

                                                }
                                            }else {

                                                r.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                final DatabaseReference r2 = reference.child("date").child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                                                        .child(month).child(day).child(String.valueOf(Integer.parseInt(time)+1));
                                                r2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(temporaryClass);

                                                String key = mDatabase.child(card.getRestaurant_info().getFirebase_id()).push().getKey();

                                                mDatabase.child(key).setValue(temporaryClass);

                                                dialog.setContentView(R.layout.a7_confirmed_dialog);
                                                dialog.setCancelable(true);
                                                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                                                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                                                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                    @Override
                                                    public void onCancel(DialogInterface dialog) {
                                                        dialog.cancel();
                                                        startActivity(new Intent(context, A1_GalleryActivity.class));
                                                    }
                                                });

                                                reserveFlag = false;

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });


                                    // no T4 exists
                                }else{

                                    Toast.makeText(A7_ReservationConfirmationActivity.this, "No children exist.", Toast.LENGTH_SHORT).show();
                                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                    mAuth.signOut();
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

        }else{

            Toast.makeText(context, "You cannot reserve this time slot due to your current running reservations.", Toast.LENGTH_SHORT).show();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            reserveFlag = false;

        }

    }
}
