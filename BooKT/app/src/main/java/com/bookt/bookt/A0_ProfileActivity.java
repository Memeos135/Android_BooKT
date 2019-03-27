package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

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
                ArrayList<A0_ReservationsHistorySetter> activeList = new ArrayList<>();
                // DO FIREBASE
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                activeList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));

                ListView listView = findViewById(R.id.listViewHistory);
                A0_HistoryAdapter a0HistoryAdapter = new A0_HistoryAdapter(context, activeList, "active");
                listView.setAdapter(a0HistoryAdapter);
            }else{

            }

        }else{

            if(FirebaseAuth.getInstance().getCurrentUser() != null) {

                ArrayList<A0_ReservationsHistorySetter> oldList = new ArrayList<>();

                oldList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                oldList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                oldList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                oldList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
                oldList.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));

                ListView listView = findViewById(R.id.listViewHistory);
                A0_HistoryAdapter a0HistoryAdapter = new A0_HistoryAdapter(context, oldList, "in-active");
                listView.setAdapter(a0HistoryAdapter);
            }else{

            }
        }
    }

    public void setupDefault(){
        ArrayList<A0_ReservationsHistorySetter> list = new ArrayList<>();

        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));
        list.add(new A0_ReservationsHistorySetter("Date: 13 March", "Time: 10:00PM", "21.802820, 39.132578", "McDonalds"));

        ListView listView = findViewById(R.id.listViewHistory);
        A0_HistoryAdapter a0HistoryAdapter = new A0_HistoryAdapter(context, list, "active");
        listView.setAdapter(a0HistoryAdapter);
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
}
