package com.bookt.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


     public static Point size;
     Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolling_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        context = this;
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton floatingActionButton = findViewById(R.id.search_floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,searchGalleryActivity.class).addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //  VVVVVVVV Our Code Start From Here *-* VVVVVVVV  //



        Display display = getWindowManager().getDefaultDisplay();
        size  = new Point();
        display.getSize(size);

        ArrayList<Gallery> galleryArrayList = new ArrayList<>();
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());
        galleryArrayList.add(new Gallery());


        RecyclerView recyclerView = findViewById(R.id.galleryRecyclerView);
        GalleryAdapter galleryAdapter = new GalleryAdapter(this,galleryArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.generateDefaultLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
               return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(galleryAdapter);
        recyclerView.setNestedScrollingEnabled(false);






    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
