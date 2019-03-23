package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class A2_RestaurantsActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
    private Context context;
    private A2_RestaurantsActivityListViewAdapter a2_restaurantsActivityListViewAdapter;
    private ArrayList<A2_RestaurantsActivityCard> list;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a2_activity_restaurants);

        context = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        // Setting up floating button dialog
        //setupFabDialog(fab);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // -------------------------------------------RecyclerView and Toolbar setup-------------------------------//

        list = new ArrayList<>();

        ListView listView = findViewById(R.id.listView);
        a2_restaurantsActivityListViewAdapter =
                new A2_RestaurantsActivityListViewAdapter(this, list, getIntent().getStringExtra("restaurant_name"));
        listView.setAdapter(a2_restaurantsActivityListViewAdapter);

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Country")
                .child("Saudi Arabia").child("cities").child("Jeddah").child("Cuisine").child("ids")
                .child(getIntent().getStringExtra("restaurant_name"));

        showWaiting();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    A2_RestaurantsActivityCard a2_restaurantsActivityCard =
                            dataSnapshot1.getValue(A2_RestaurantsActivityCard.class);

                    if(dataSnapshot1.child("reviews").getChildrenCount() > 0){
                        a2_restaurantsActivityCard.getReviews().setReviewCount(dataSnapshot1.child("reviews").getChildrenCount());
                    }

                    list.add(a2_restaurantsActivityCard);
                }
                a2_restaurantsActivityListViewAdapter.updateList(list);
                a2_restaurantsActivityListViewAdapter.notifyDataSetChanged();
                cancelWaiting();
                mDatabase.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Please make sure you have internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
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
            context.startActivity(new Intent(context, A1_GalleryActivity.class));
        } else if (id == R.id.Profile) {
            context.startActivity(new Intent(context, A0_ProfileActivity.class));
        } else if (id == R.id.Signup) {
            context.startActivity(new Intent(context, A0_SignupActivity.class));
        } else if (id == R.id.Signin) {
            context.startActivity(new Intent(context, A0_LoginActivity.class));
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

//    // Setting up FloatingActionButton Filter Dialog
//    public void setupFabDialog(FloatingActionButton fab){
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Filter View Custom Dialog
//                final Dialog dialog = new Dialog(A2_RestaurantsActivity.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.a2_gallery_filter_view_hold);
//                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//                dialog.getWindow().setGravity(Gravity.TOP);
//                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//                dialog.setCancelable(true);
//                dialog.show();
//
//                // Filter Listview arraylist set-up for testing
//                final ArrayList<A2_RestaurantFilterSetter> filterList = new ArrayList<>();
//                filterList.add(new A2_RestaurantFilterSetter("HELLO", false));
//                filterList.add(new A2_RestaurantFilterSetter("WORLD", false));
//
//                final A2_RestaurantsFilterCustomListAdapter_HOLD customFilterListVewAdapter =
//                        new A2_RestaurantsFilterCustomListAdapter_HOLD(getApplicationContext(), filterList);
//
//                ListView listView = dialog.findViewById(R.id.filterlistView);
//                listView.setAdapter(customFilterListVewAdapter);
//
//                // Process okButton Press
//                Button okButton = dialog.findViewById(R.id.okButton);
//                okButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.cancel();
//                        Toast.makeText(A2_RestaurantsActivity.this, "OK PRESSED", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                // Process clearButton press
//                Button clearButton = dialog.findViewById(R.id.clearAllButton);
//                clearButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        for(int i = 0; i < filterList.size(); i++){
//                            filterList.get(i).setChecked(false);
//                        }
//                        customFilterListVewAdapter.notifyDataSetChanged();
//                    }
//                });
//            }
//        });
//    }

    public void showWaiting(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = (ViewGroup)findViewById(R.id.coordinatorLayout);
        inflater.inflate(R.layout.waiting_animation, parent);
    }

    public void cancelWaiting(){
        ProgressBar progressBar = findViewById(R.id.waitProgressBar);
        ((ViewGroup)progressBar.getParent()).removeView(progressBar);
    }
}
