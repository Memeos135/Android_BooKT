package com.bookt.bookt;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class A0_ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a0_profile_activity);

        context = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.signed_drawer);
        }

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            setupDefault();
        }else{
            ((TextView) findViewById(R.id.profilePersonName)).setText("");
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            context.startActivity(new Intent(context, A1_GalleryActivity.class));
        } else if (id == R.id.Profile) {
            context.startActivity(new Intent(context, A0_ProfileActivity.class));
        } else if (id == R.id.Signup) {
            context.startActivity(new Intent(context, A0_SignupActivity.class));
        } else if (id == R.id.Signin) {
            context.startActivity(new Intent(context, A0_LoginActivity.class)
            .putExtra("activity", "A0_ProfileActivity"));
        } else if(id == R.id.Signout) {
            FirebaseAuth.getInstance().signOut();
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_gallery_drawer);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void radioHandler(View v){
        if (v.getTag().equals("active")){
            // READ ACTIVE RESERVATIONS FROM FIREBASE

            if(FirebaseAuth.getInstance().getCurrentUser()!= null) {
                final ArrayList<A0_ReservationsHistorySetter> activeList = new ArrayList<>();

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("reservations")
                        .child("active");

                showWaiting();

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
//                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                                 A0_ReservationsHistorySetter profileReservationsSetter = dataSnapshot1.getValue(A0_ReservationsHistorySetter.class);
//                                 activeList.add(profileReservationsSetter);
//                            }
//                        ListView listView = findViewById(R.id.listViewHistory);
//                        A0_HistoryAdapter a0HistoryAdapter = new A0_HistoryAdapter(context, activeList, "active");
//                        listView.setAdapter(a0HistoryAdapter);
//                        }
                        cancelWaiting();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }else{

            }

        }else{

            if(FirebaseAuth.getInstance().getCurrentUser() != null) {

                final ArrayList<A0_ReservationsHistorySetter> oldList = new ArrayList<>();

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("reservations")
                        .child("in-active");

                showWaiting();

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
//                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                                 A0_ReservationsHistorySetter profileReservationsSetter = dataSnapshot1.getValue(A0_ReservationsHistorySetter.class);
//                                 oldList.add(profileReservationsSetter);
//                            }
//                        ListView listView = findViewById(R.id.listViewHistory);
//                        A0_HistoryAdapter a0HistoryAdapter = new A0_HistoryAdapter(context, oldList, "in-active");
//                        listView.setAdapter(a0HistoryAdapter);
//                        }
                        cancelWaiting();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }else{

            }
        }
    }

    public void setupDefault(){

        final TextView userName = findViewById(R.id.profilePersonName);
        final ListView listView = findViewById(R.id.listViewHistory);

        final ArrayList<A0_ReservationsHistorySetter> list = new ArrayList<>();

        // READ PROFILE INFORMATION

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users")
                .child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid()));

        showWaiting();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    userName.setText(dataSnapshot.child("profile_info").child("name").getValue().toString());

                    if(dataSnapshot.child("reservations").child("active").exists()
                            && dataSnapshot.child("reservations").child("active").getChildrenCount() > 0){
//                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                                 A0_ReservationsHistorySetter profileReservationsSetter = dataSnapshot1.getValue(A0_ReservationsHistorySetter.class);
//                                 oldList.add(profileReservationsSetter);
//                            }
//                        ListView listView = findViewById(R.id.listViewHistory);
//                        A0_HistoryAdapter a0HistoryAdapter = new A0_HistoryAdapter(context, oldList, "in-active");
//                        listView.setAdapter(a0HistoryAdapter);
                    }

                }
                cancelWaiting();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.signed_drawer);
            setupDefault();
        }else{
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_gallery_drawer);
        }
    }

    public void showWaiting(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = (ViewGroup)findViewById(R.id.constraint);
        inflater.inflate(R.layout.waiting_animation, parent);
    }

    public void cancelWaiting(){
        ProgressBar progressBar = findViewById(R.id.waitProgressBar);
        ((ViewGroup)progressBar.getParent()).removeView(progressBar);
    }

    public void editDialog(View v){

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {

            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.profile_edit_dialog);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(true);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.create();
            }
            dialog.show();

        }
    }
}
