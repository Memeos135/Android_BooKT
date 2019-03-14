package com.bookt.bookt;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class A1_GalleryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a1_activity_gallery);

        context = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        //-----------------------------------------Floating Button Functions-------------------------------------------//
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // SearchView Custom Dialog
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.a1_search_view);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.TOP);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCancelable(true);
                dialog.create();
                dialog.show();

                // ListView Linking and Adapter set-up and test
                ListView listview = dialog.findViewById(R.id.listView);

//                // Linking filter icon of SearchView

                // Fill Up Dialog ListView Array
                ArrayList<A1_SearchViewResultsSetter> x = new ArrayList<>();
                x.add(new A1_SearchViewResultsSetter("Burger King", "Northern Obhur"));
                x.add(new A1_SearchViewResultsSetter("McDonalds", "Southern Obhur"));
                x.add(new A1_SearchViewResultsSetter("McDonalds", "Southern Obhur"));
                x.add(new A1_SearchViewResultsSetter("McDonalds", "Southern Obhur"));
                x.add(new A1_SearchViewResultsSetter("McDonalds", "Southern Obhur"));
                x.add(new A1_SearchViewResultsSetter("McDonalds", "Southern Obhur"));

                A1_SearchViewResultsListAdapter a1SearchViewResultsListAdapter = new A1_SearchViewResultsListAdapter(context, x);
                listview.setAdapter(a1SearchViewResultsListAdapter);

                ImageView imageView = dialog.findViewById(R.id.closeImage);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
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
        ArrayList<A1_GalleryActivityCard> list = new ArrayList<>();
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));
        list.add(new A1_GalleryActivityCard("hello",""));


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        // Disable cursor focus on RecyclerView (do not point cursor to recyclerView as default)
        recyclerView.setFocusable(false);
        // Disable nestedScroll because we're using NestedScrollView
        recyclerView.setNestedScrollingEnabled(false);

        NestedScrollView nestedScrollView = findViewById(R.id.nestedScrollView);
        // Point cursor focus to the start of NestedScrollView (default)
        nestedScrollView.requestFocus();

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,13);
        gridLayoutManager.generateDefaultLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            // default - MUST implement
            @Override
            public int getSpanSize(int i) {
                // i = grid cell number
                if(i == 0){

                    return 0;

                }else if(i%5 == 0){

                    // give this cell full screen width, as 13 is the number of columns of the recycler's grid layout manager
                   return 13;

               }else{

                    // logic to implement dynamic cell widths for those other than every 5th
                   if(i%2 == 0){

                       if(gridLayoutManager.getSpanSizeLookup().getSpanSize(i-2) == 7){
                           return 6;
                       }else{
                           return 7;
                       }

                   }else{

                       if(gridLayoutManager.getSpanSizeLookup().getSpanSize(i-2) == 6){
                           return 7;
                       }else{
                           return 6;
                       }

                   }
               }
            }

        });

        A1_GalleryActivityRecyclerViewAdapter recycleViewAdpaterGallery = new A1_GalleryActivityRecyclerViewAdapter(this,list);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recycleViewAdpaterGallery);
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

    // location image animation
    public void locationAnimation(View v){
        ImageView locationImageView;
        locationImageView = findViewById(R.id.locationImage);

        locationImageView.clearAnimation();
        locationImageView.setRotation(360.0F);
        locationImageView.animate().rotation(locationImageView.getRotation()+ 360.0F).setDuration(500L);
    }
}
