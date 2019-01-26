package com.bookt.bookt;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GalleryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    GalleryActivityRecyclerViewAdapter recycleViewAdpaterGalleryLeft;
    int click = 0;
    Display mdisp;
    Point mdispSize;
    ConstraintLayout searchView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // Linking SearchView
        searchView = findViewById(R.id.searchVew);

        // Fetching screen resolution
        mdisp = getWindowManager().getDefaultDisplay();
        mdispSize = new Point();
        mdisp.getSize(mdispSize);

        // Click listener for Floating Button to track number of clicks and translate search view Out or Into screen
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click++;
                System.out.println(click);
                if(click%2 == 0){
                    click = 0;
                    searchView.animate().translationY(mdispSize.y).setDuration(500);
                }else{
                    searchView.animate().translationY(0).setDuration(500);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // -------------------------------------------RecyclerView and Toolbar setup---------------------------------//
        ArrayList<GalleryActivityCard> list = new ArrayList<>();
        list.add(new GalleryActivityCard("hello",""));
        list.add(new GalleryActivityCard("hello",""));
        list.add(new GalleryActivityCard("hello",""));
        list.add(new GalleryActivityCard("hello",""));
        list.add(new GalleryActivityCard("hello",""));
        list.add(new GalleryActivityCard("hello",""));
        list.add(new GalleryActivityCard("hello",""));
        list.add(new GalleryActivityCard("hello",""));

        recyclerView = findViewById(R.id.recyclerView);
        // Disable cursor focus on RecyclerView (do not point cursor to recyclerView as default)
        recyclerView.setFocusable(false);
        // Disable nestedScroll because we're using NestedScrollView
        recyclerView.setNestedScrollingEnabled(false);

        nestedScrollView = findViewById(R.id.nestedScrollView);
        // Point cursor focus to the start of NestedScrollView (default)
        nestedScrollView.requestFocus();

        // Set scroll listener to dynamically collapse and reset Toolbar
        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY || scrollY < oldScrollY) {
                    // RESETTING CLICKS TO AVOID ERRORS
                    click = 0;
                    // CHECK SCREEN RESOLUTION AND TRANSLATE ACCORDINGLY
                    searchView.animate().translationY(mdispSize.y).setDuration(500);
                }
            }
        });

        recycleViewAdpaterGalleryLeft = new GalleryActivityRecyclerViewAdapter(this,list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycleViewAdpaterGalleryLeft);
        // --------------------------------------------------------------------------------------------------------//

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
