package com.bookt.bookt;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

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
    ImageView backImageView;
    ImageView locationImageView;
    ImageView filterImageView;
    ListView listview;

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

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // Linking SearchView
        searchView = findViewById(R.id.searchVew);

        // ListView Linking and Adapter set-up and test
        listview = searchView.findViewById(R.id.listView);
        ArrayList<String> x = new ArrayList<>();
        x.add("HELLO");
        x.add("WORLD");
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, x);
        listview.setAdapter(ad);

        // Linking to backImageView
        backImageView = findViewById(R.id.backImageView);
        // Setting listener
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = 0;
                searchView.animate().translationY(mdispSize.y).setDuration(500);
            }
        });

        // Linking filter icon of SearchView
        filterImageView = searchView.findViewById(R.id.imageView2);

        // Setting ALL LISTENERS for filter icon of SearchView
        filterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listview.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        // Disable click-through if filter view is VISIBLE
                        if(searchView.findViewById(R.id.cusom_filter).getVisibility()==View.VISIBLE) {
                            return true;
                            // Enable click-through if filter view is INVISIBLE
                        }else{
                            return false;
                        }
                    }
                });
                // Enable visibility of filter view as filter icon is pressed
                searchView.findViewById(R.id.cusom_filter).setVisibility(View.VISIBLE);

                // Filter Listview arraylist set-up for testing
                ArrayList<GalleryFilterSetter> filterList = new ArrayList<>();
                filterList.add(new GalleryFilterSetter("HELLO"));
                filterList.add(new GalleryFilterSetter("WORLD"));

                GalleryFilterCustomListAdapter customFilterListVewAdapter =
                        new GalleryFilterCustomListAdapter(getApplicationContext(), filterList);

                ListView listView = (ListView) searchView.findViewById(R.id.filterlistView);
                listView.setAdapter(customFilterListVewAdapter);

                final ImageView closeImage = (ImageView) searchView.findViewById(R.id.filterClose);

                // Filter close image on click listener
                closeImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Disable visibility of filter view
                        searchView.findViewById(R.id.cusom_filter).setVisibility(View.INVISIBLE);
                    }
                });

                // Process okButton Press
                Button okButton = (Button) searchView.findViewById(R.id.okButton);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(GalleryActivity.this, "OK PRESSED", Toast.LENGTH_SHORT).show();
                    }
                });

                // Process clearButton press
                Button clearButton = (Button) searchView.findViewById(R.id.clearAllButton);
                clearButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(GalleryActivity.this, "CLEAR PRESSED", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Linking Toolbar locationImage
        locationImageView = toolbar.findViewById(R.id.imageView6);

        // Animation Listener
        locationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationImageView.clearAnimation();
                locationImageView.setRotation(360);
                locationImageView.animate().rotation(locationImageView.getRotation()+360).setDuration(500);
            }
        });

        // Fetching screen resolution
        mdisp = getWindowManager().getDefaultDisplay();
        mdispSize = new Point();
        mdisp.getSize(mdispSize);

        // Click listener for Floating Button to track number of clicks and translate search view Out or Into screen
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click++;
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

        // Set touch listener to disable scroll when SearchView is expanded
        nestedScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(searchView.getY() == 0) {
                    return true;
                }else{
                   return false;
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
            // If android Keyboard.BACK is pressed, check if searchView is currently in-screen
        } else if(searchView.getY() == 0){
            click = 0;
            searchView.animate().translationY(mdispSize.y).setDuration(500);
            searchView.findViewById(R.id.cusom_filter).setVisibility(View.INVISIBLE);
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
