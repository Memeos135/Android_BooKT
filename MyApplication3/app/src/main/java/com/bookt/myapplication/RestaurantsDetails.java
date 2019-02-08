package com.bookt.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;

public class RestaurantsDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    MenuAdapter menuAdapter;
    ArrayList<Type> typeArrayList;
    ArrayList<Item> itemArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        typeArrayList = new ArrayList<>();
        itemArrayList = new ArrayList<>();


        itemArrayList.add(new Item());
        itemArrayList.add(new Item());
        itemArrayList.add(new Item());
        itemArrayList.add(new Item());
        itemArrayList.add(new Item());
        itemArrayList.add(new Item());


        typeArrayList.add(new Type("test1",itemArrayList));
        typeArrayList.add(new Type("test2",itemArrayList));
        typeArrayList.add(new Type("test3",itemArrayList));
        typeArrayList.add(new Type("test4",itemArrayList));

        typeArrayList.add(new Type("test1",itemArrayList));
        typeArrayList.add(new Type("test2",itemArrayList));
        typeArrayList.add(new Type("test3",itemArrayList));
        typeArrayList.add(new Type("test4",itemArrayList));

        typeArrayList.add(new Type("test1",itemArrayList));
        typeArrayList.add(new Type("test2",itemArrayList));
        typeArrayList.add(new Type("test3",itemArrayList));
        typeArrayList.add(new Type("test4",itemArrayList));

        typeArrayList.add(new Type("test1",itemArrayList));
        typeArrayList.add(new Type("test2",itemArrayList));
        typeArrayList.add(new Type("test3",itemArrayList));
        typeArrayList.add(new Type("test4",itemArrayList));

        typeArrayList.add(new Type("test1",itemArrayList));
        typeArrayList.add(new Type("test2",itemArrayList));
        typeArrayList.add(new Type("test3",itemArrayList));
        typeArrayList.add(new Type("test4",itemArrayList));

        typeArrayList.add(new Type("test1",itemArrayList));
        typeArrayList.add(new Type("test2",itemArrayList));
        typeArrayList.add(new Type("test3",itemArrayList));
        typeArrayList.add(new Type("test4",itemArrayList));







        recyclerView = findViewById(R.id.details_recycler);
        menuAdapter = new MenuAdapter(typeArrayList,this,typeArrayList,itemArrayList);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(menuAdapter);


        recyclerView.setFocusable(false);

        final MapFragment mapFragment = new MapFragment();











    }


}
