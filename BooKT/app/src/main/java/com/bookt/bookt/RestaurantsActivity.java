package com.bookt.bookt;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
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
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener

    {
        RecyclerView recyclerView;
        RestaurantsActivityRecyclerViewAdapter recyclerViewRestaurantsAdapter;
        NestedScrollView nestedScrollView;
        ImageView imageView;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurants);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Animating Toolbar Icon for Simple Effect
        imageView = toolbar.findViewById(R.id.toolbarImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ANIMATE FILTER VIEW
                imageView.animate().rotation(90).setDuration(1000);
                Toast.makeText(RestaurantsActivity.this, "Click Listener Works!", Toast.LENGTH_SHORT).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // -------------------------------------------RecyclerView and Toolbar setup-------------------------------//
        ArrayList<RestaurantsActivityCard> list = new ArrayList<>();
        list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                "Jeddah", 3, "10", "12"));
            list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                    "Jeddah", 3, "10", "12"));
            list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                    "Jeddah", 3, "10", "12"));
            list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                    "Jeddah", 3, "10", "12"));
            list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                    "Jeddah", 3, "10", "12"));
            list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                    "Jeddah", 3, "10", "12"));
            list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                    "Jeddah", 3, "10", "12"));
            list.add(new RestaurantsActivityCard("Restaurant Test", "Spaghetti",
                    "Jeddah", 3, "10", "12"));

            recyclerView = findViewById(R.id.recyclerViewRestaurants);
            // Disable cursor focus on RecyclerView (do not point cursor to recyclerView as default)
            recyclerView.setFocusable(false);
            // Disable nestedScroll because we're using NestedScrollView
            recyclerView.setNestedScrollingEnabled(false);

            nestedScrollView = findViewById(R.id.nestedScrollView);
            // Point cursor focus to the start of NestedScrollView (default)
            nestedScrollView.requestFocus();

            recyclerViewRestaurantsAdapter = new RestaurantsActivityRecyclerViewAdapter(this, list);

            recyclerView.setAdapter(recyclerViewRestaurantsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // --------------------------------------------------------------------------------------------------------//

    }

        @Override
        public void onBackPressed () {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

        @Override
        public boolean onPrepareOptionsMenu(Menu menu) {
            System.out.println("HELLO WORLD");
            return true;
        }

//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            // Handle action bar item clicks here. The action bar will
//            // automatically handle clicks on the Home/Up button, so long
//            // as you specify a parent activity in AndroidManifest.xml.
//            int id = item.getItemId();
//
//            //noinspection SimplifiableIfStatement
//            if (id == R.id.action_settings) {
//                return true;
//            }
//
//            return super.onOptionsItemSelected(item);
//        }


        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected (MenuItem item){
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