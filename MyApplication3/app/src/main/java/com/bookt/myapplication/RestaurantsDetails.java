package com.bookt.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class RestaurantsDetails extends AppCompatActivity implements ReserveFragment.OnFragmentInteractionListener , MenuFragment.OnFragmentInteractionListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);




        TabLayout tabLayout = findViewById(R.id.tabLayout);


        tabLayout.addTab(tabLayout.newTab().setText("Reserve"));
        tabLayout.addTab(tabLayout.newTab().setText("Menu"));


        final ViewPager viewPager = findViewById(R.id.viewPagerTab);
        PagesAdapter pagesAdapter = new PagesAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagesAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

















    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
