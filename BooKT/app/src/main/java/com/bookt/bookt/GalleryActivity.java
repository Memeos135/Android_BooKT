package com.bookt.bookt;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery);

        context = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        //-----------------------------------------Floating Button Functions-------------------------------------------//
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SearchView Custom Dialog
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.search_view);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCancelable(true);
                dialog.create();
                dialog.show();

                // ListView Linking and Adapter set-up and test
                ListView listview = dialog.findViewById(R.id.listView);

                // Linking filter icon of SearchView
                ImageView filterImageView = dialog.findViewById(R.id.filterImage);

                // Fill Up Dialog ListView Array
                ArrayList<SearchViewResultsSetter> x = new ArrayList<>();
                x.add(new SearchViewResultsSetter("Burger King", "Northern Obhur"));
                x.add(new SearchViewResultsSetter("McDonalds", "Southern Obhur"));
                x.add(new SearchViewResultsSetter("McDonalds", "Southern Obhur"));
                x.add(new SearchViewResultsSetter("McDonalds", "Southern Obhur"));
                x.add(new SearchViewResultsSetter("McDonalds", "Southern Obhur"));
                x.add(new SearchViewResultsSetter("McDonalds", "Southern Obhur"));

                SearchViewResultsListAdapter searchViewResultsListAdapter = new SearchViewResultsListAdapter(context, x);
                listview.setAdapter(searchViewResultsListAdapter);

                // Setting ALL LISTENERS for filter icon of SearchView
                filterImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Filter View Custom Dialog
                        Dialog dialog = new Dialog(GalleryActivity.this);
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
                                Toast.makeText(GalleryActivity.this, "OK PRESSED", Toast.LENGTH_SHORT).show();
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
        });
        //------------------------------------------------------------------------------------------------------------//

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // -------------------------------------------RecyclerView setup--------------------------------------------//
        ArrayList<GalleryActivityCard> list = new ArrayList<>();
        list.add(new GalleryActivityCard("hello",""));
        list.add(new GalleryActivityCard("hello",""));
        list.add(new GalleryActivityCard("hello",""));
        list.add(new GalleryActivityCard("hello",""));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        // Disable cursor focus on RecyclerView (do not point cursor to recyclerView as default)
        recyclerView.setFocusable(false);
        // Disable nestedScroll because we're using NestedScrollView
        recyclerView.setNestedScrollingEnabled(false);

        NestedScrollView nestedScrollView = findViewById(R.id.nestedScrollView);
        // Point cursor focus to the start of NestedScrollView (default)
        nestedScrollView.requestFocus();

        GalleryActivityRecyclerViewAdapter recycleViewAdpaterGallery = new GalleryActivityRecyclerViewAdapter(this,list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycleViewAdpaterGallery);
        // --------------------------------------------------------------------------------------------------------//

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
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

    // location image animation
    public void locationAnimation(View v){
        ImageView locationImageView;
        locationImageView = findViewById(R.id.locationIcon);

        locationImageView.clearAnimation();
        locationImageView.setRotation(360.0F);
        locationImageView.animate().rotation(locationImageView.getRotation()+ 360.0F).setDuration(500L);
    }
}
