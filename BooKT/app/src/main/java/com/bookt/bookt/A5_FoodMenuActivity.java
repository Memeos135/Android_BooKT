package com.bookt.bookt;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class A5_FoodMenuActivity extends AppCompatActivity implements A5_MenuTabLayoutAdapter.MenuFragment.OnFragmentInteractionListener {

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a5_food_menu_activity);

        context = this;

        TabLayout tabLayout = findViewById(R.id.menuTabLayout);

        readTabs(tabLayout);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void backImage(View v){
        onBackPressed();
    }

    public void readTabs(final TabLayout tabLayout){
        // read tabs from firebase and store into array, then loop and assign it to tabLayout

        final ArrayList<A5_MenuItemsSetter> list = new ArrayList<>();

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Restaurants")
                .child(getIntent().getStringExtra("id"))
                .child("menuItems");

        showWaiting();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    A5_MenuItemsSetter a5_menuItemsSetter = dataSnapshot1.getValue(A5_MenuItemsSetter.class);

                    tabLayout.addTab(tabLayout.newTab().setText(a5_menuItemsSetter.getMenuCategory()));

                    list.add(a5_menuItemsSetter);

                }

                ViewPager viewPager = findViewById(R.id.menuViewPager);
                setupViewPager(tabLayout, viewPager, list);

                cancelWaiting();
                mDatabase.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setupViewPager(final TabLayout tabLayout, final ViewPager viewPager, final ArrayList<A5_MenuItemsSetter> list){

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());

        // should send the tab arraylist into the adapter if reading tabs from firebase
        A5_MenuTabLayoutAdapter a5MenuTabLayoutAdapter = new A5_MenuTabLayoutAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), list);

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

    public void showWaiting(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = (ViewGroup)findViewById(R.id.menuConstraintLayout);
        inflater.inflate(R.layout.waiting_animation, parent);
    }

    public void cancelWaiting(){
        ProgressBar progressBar = findViewById(R.id.waitProgressBar);
        ((ViewGroup)progressBar.getParent()).removeView(progressBar);
    }
}
