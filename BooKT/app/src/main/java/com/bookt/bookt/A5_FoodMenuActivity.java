package com.bookt.bookt;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class A5_FoodMenuActivity extends AppCompatActivity implements A5_MenuTabLayoutAdapter.MenuFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a5_food_menu_activity);

        TabLayout tabLayout = findViewById(R.id.menuTabLayout);

        ViewPager viewPager = findViewById(R.id.menuViewPager);

        readTabs(tabLayout);

        setupViewPager(tabLayout, viewPager);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void backImage(View v){
        onBackPressed();
    }

    public void readTabs(TabLayout tabLayout){
        // read tabs from firebase and store into array, then loop and assign it to tabLayout

        tabLayout.addTab(tabLayout.newTab().setText("Starters"));
        tabLayout.addTab(tabLayout.newTab().setText("Main Dish"));
        tabLayout.addTab(tabLayout.newTab().setText("Dessert"));
        tabLayout.addTab(tabLayout.newTab().setText("Drinks"));
        tabLayout.addTab(tabLayout.newTab().setText("Sandwiches"));
        tabLayout.addTab(tabLayout.newTab().setText("Steaks"));
        tabLayout.addTab(tabLayout.newTab().setText("Pizza"));

    }

    public void setupViewPager(final TabLayout tabLayout, final ViewPager viewPager){

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());

        // should send the tab arraylist into the adapter if reading tabs from firebase
        A5_MenuTabLayoutAdapter a5MenuTabLayoutAdapter = new A5_MenuTabLayoutAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(a5MenuTabLayoutAdapter);

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
        // empty - must be implemented
    }
}
