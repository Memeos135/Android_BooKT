package com.bookt.myapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ArrayList<Restaurants> restaurants = new ArrayList<>();

        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());
        restaurants.add(new Restaurants());


        RecyclerView recyclerView = findViewById(R.id.recyclerViewRestaurants);
        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(this,restaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(restaurantsAdapter);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
