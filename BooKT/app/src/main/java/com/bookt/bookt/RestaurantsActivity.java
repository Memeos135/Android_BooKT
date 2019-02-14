package com.bookt.bookt;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurants);

        context = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_filter_list_black_24dp);

        // Setting up floating button dialog
        setupFabDialog(fab);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // -------------------------------------------RecyclerView and Toolbar setup-------------------------------//
        ArrayList<RestaurantsActivityCard> list = new ArrayList<>();
        list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                "Jeddah", 3, "10:00" + "pm ", "12:00" + "pm"));
        list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                "Jeddah", 3, "10:00" + "pm", "12:00" + "pm"));
        list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                "Jeddah", 3, "10:00" + "pm", "12:00" + "pm"));
        list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                "Jeddah", 3, "10:00" + "pm", "12:00" + "pm"));


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        // Disable cursor focus on RecyclerView (do not point cursor to recyclerView as default)
        recyclerView.setFocusable(false);
        // Disable nestedScroll because we're using NestedScrollView
        recyclerView.setNestedScrollingEnabled(false);

        NestedScrollView nestedScrollView = findViewById(R.id.nestedScrollView);
        // Point cursor focus to the start of NestedScrollView (default)
        nestedScrollView.requestFocus();

        RestaurantsActivityRecyclerViewAdapter recyclerViewRestaurantsAdapter = new RestaurantsActivityRecyclerViewAdapter(this, list);

        recyclerView.setAdapter(recyclerViewRestaurantsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // --------------------------------------------------------------------------------------------------------//
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            context.startActivity(new Intent(context, GalleryActivity.class));
        } else if (id == R.id.Profile) {

        } else if (id == R.id.Reservations) {

        } else if (id == R.id.History) {

        } else if (id == R.id.Signup) {

        } else if (id == R.id.Signin) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void locationAnimation(View view) {
        ImageView locationImageView = findViewById(R.id.locationImage);
        locationImageView.clearAnimation();
        locationImageView.setRotation(360.0F);
        locationImageView.animate().rotation(locationImageView.getRotation() + 360.0F).setDuration(500L);
    }

    // Setting up FloatingActionButton Filter Dialog
    public void setupFabDialog(FloatingActionButton fab){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Filter View Custom Dialog
                Dialog dialog = new Dialog(RestaurantsActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.gallery_filter_view);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCancelable(true);
                dialog.show();

                // Filter Listview arraylist set-up for testing
                final ArrayList<GalleryFilterSetter> filterList = new ArrayList<>();
                filterList.add(new GalleryFilterSetter("HELLO", false));
                filterList.add(new GalleryFilterSetter("WORLD", false));

                final GalleryFilterCustomListAdapter customFilterListVewAdapter =
                        new GalleryFilterCustomListAdapter(getApplicationContext(), filterList);

                ListView listView = dialog.findViewById(R.id.filterlistView);
                listView.setAdapter(customFilterListVewAdapter);

                // Process okButton Press
                Button okButton = dialog.findViewById(R.id.okButton);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(RestaurantsActivity.this, "OK PRESSED", Toast.LENGTH_SHORT).show();
                    }
                });

                // Process clearButton press
                Button clearButton = dialog.findViewById(R.id.clearAllButton);
                clearButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i = 0; i < filterList.size(); i++){
                            filterList.get(i).setChecked(false);
                        }
                        customFilterListVewAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
