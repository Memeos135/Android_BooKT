package com.bookt.bookt;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Locale;

public class RestaurantDetailsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    ImageView locationImageView;
    ExpandableListView expandableListView;
    ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<Object> childItem = new ArrayList<Object>();
    MapView mapView;
    GoogleMap mMap;
    Context context;
    Button reserveButton;
    int expandableListViewBaseHeight = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        context = this;

        setGroupData();
        setChildGroupData();

        // gallery images listener
        setGalleryImageListener();

        // Listener for RESERVE button
        reserveButton = findViewById(R.id.reserveButton);
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "RESERVE PRESSED", Toast.LENGTH_SHORT).show();
            }
        });

        // mapview setup
        mapView = findViewById(R.id.mapView2);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //galleryImagesSizeText = findViewById(R.id.textView15);

        // expandablelistview setup and adapter linking
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        expandableListView.setFocusable(false);

        ExpandableListViewAdapter mNewAdapter = new ExpandableListViewAdapter(groupItem, childItem);
        mNewAdapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        expandableListView.setAdapter(mNewAdapter);

        // check expandable listview height
        checkExpandableListViewHeight();

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                // Used when group collapses and group items are BIGGER than 5
                if(expandableListViewBaseHeight == 0 && groupItem.size() > 5){
                    expandableListViewBaseHeight = expandableListView.getLayoutParams().height;
                }
                ListAdapter listadp = expandableListView.getAdapter();
                if (listadp != null) {
                    int totalHeight = 0;
                    for (int j = 0; j < listadp.getCount(); j++) {
                        View listItem = listadp.getView(j, null, expandableListView);
                        listItem.measure(0, 0);
                        totalHeight += listItem.getMeasuredHeight();
                    }
                    ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
                    params.height = totalHeight + (expandableListView.getDividerHeight() * (listadp.getCount() - 1));
                    expandableListView.setLayoutParams(params);
                    expandableListView.requestLayout();
                }
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                // check if group items are BIGGER than 5, if so return to base size
                if(groupItem.size() > 5){
                    ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
                    params.height = expandableListViewBaseHeight;
                    expandableListView.setLayoutParams(params);
                    expandableListView.requestLayout();
                }else{
                    ListAdapter listadp = expandableListView.getAdapter();
                    if (listadp != null) {
                        int totalHeight = 0;
                        for (int j = 0; j < listadp.getCount(); j++) {
                            View listItem = listadp.getView(j, null, expandableListView);
                            listItem.measure(0, 0);
                            totalHeight += listItem.getMeasuredHeight();
                        }
                        ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
                        params.height = totalHeight + (expandableListView.getDividerHeight() * (listadp.getCount() - 1));
                        expandableListView.setLayoutParams(params);
                        expandableListView.requestLayout();
                    }
                }
            }
        });

        // ENABLE NESTED SCROLLING OF EXPANDED LIST VIEW
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            expandableListView.setNestedScrollingEnabled(true);
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Listener for CALL icon within toolbar
        toolbar.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "CALL PRESSED", Toast.LENGTH_SHORT).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        locationImageView = toolbar.findViewById(R.id.imageView6);

        locationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationImageView.clearAnimation();
                locationImageView.setRotation(360);
                locationImageView.animate().rotation(locationImageView.getRotation() + 360).setDuration(500);
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            context.startActivity(new Intent(context, GalleryActivity.class));
        } else if (id == R.id.Profile) {

        } else if (id == R.id.Reservations) {

        } else if (id == R.id.History) {

        } else if (id == R.id.Signup) {

        } else if (id == R.id.Signin) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // fill the GROUP of expandablelistview
    public void setGroupData() {
        groupItem.add("TechNology");
        groupItem.add("Mobile");
        groupItem.add("Manufacturer");
        groupItem.add("Extras");
        groupItem.add("Science");
        groupItem.add("Business");
        groupItem.add("Medicine");
    }

    // fill in the childen of expandablelistview GROUP
    public void setChildGroupData() {
        /**
         * Add Data For TecthNology
         */
        ArrayList<String> child = new ArrayList<String>();
        child.add("Java");
        child.add("Drupal");
        child.add(".Net Framework");
        child.add("PHP");
        childItem.add(child);

        /**
         * Add Data For Mobile
         */
        child = new ArrayList<String>();
        child.add("Android");
        child.add("Window Mobile");
        child.add("iPHone");
        child.add("Blackberry");
        childItem.add(child);
        /**
         * Add Data For Manufacture
         */
        child = new ArrayList<String>();
        child.add("HTC");
        child.add("Apple");
        child.add("Samsung");
        child.add("Nokia");
        childItem.add(child);
        /**
         * Add Data For Extras
         */
        child = new ArrayList<String>();
        child.add("Contact Us");
        child.add("About Us");
        child.add("Location");
        child.add("Root Cause");
        childItem.add(child);
        /**
         * Add Data For Science
         */
        child = new ArrayList<String>();
        child.add("Contact Us");
        child.add("About Us");
        child.add("Location");
        child.add("Root Cause");
        childItem.add(child);
        /**
         * Add Data For Business
         */
        child = new ArrayList<String>();
        child.add("Contact Us");
        child.add("About Us");
        child.add("Location");
        child.add("Root Cause");
        childItem.add(child);
        /**
         * Add Data For Medicine
         */
        child = new ArrayList<String>();
        child.add("Contact Us");
        child.add("About Us");
        child.add("Location");
        child.add("Root Cause");
        childItem.add(child);

    }

    // when map is setup, and view is opened, pin this location, zoom, and add marker
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng latLng = new LatLng(21.802820, 39.132578);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)      // Sets the center of the map to Mountain View
                .zoom(15)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        // Used to add Marker to Google Maps
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(cameraPosition.target);
        mMap.addMarker(markerOptions);

        // Click listener for mapview to open Google Maps and pin marker
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Load alert dialog
                final AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);

                builder.setTitle("Google Maps").setMessage("Would you like to open Google Maps?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // pass required parameters to google maps for pin marker
                                String uri = String.format(Locale.ENGLISH, "geo:<" +
                                                String.valueOf(latLng.latitude) + ">,<" +
                                                String.valueOf(latLng.longitude) + ">?q=<" +
                                                String.valueOf(latLng.latitude) + ">,<" +
                                                String.valueOf(latLng.longitude) + ">",
                                        latLng.latitude, latLng.longitude);

                                // start the google maps activity
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                context.startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_map).show();
            }
        });

    }

    public void checkExpandableListViewHeight(){
        // if GROUP items are less than 5, edit the height to wrap its size
        if(groupItem.size() < 5){
            ListAdapter listadp = expandableListView.getAdapter();
            if (listadp != null) {
                int totalHeight = 0;
                for (int j = 0; j < listadp.getCount(); j++) {
                    View listItem = listadp.getView(j, null, expandableListView);
                    listItem.measure(0, 0);
                    totalHeight += listItem.getMeasuredHeight();
                }
                ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
                params.height = totalHeight + (expandableListView.getDividerHeight() * (listadp.getCount() - 1));
                expandableListView.setLayoutParams(params);
                expandableListView.requestLayout();
            }
        }
    }

    public void setGalleryImageListener(){
        LinearLayout linearLayout = findViewById(R.id.linearLayout1);
        LinearLayout linearLayout1 = findViewById(R.id.linearLayout2);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Image Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Image Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
