package com.bookt.bookt;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Locale;

public class RestaurantDetailsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    ExpandableListView expandableListView;
    ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<Object> childItem = new ArrayList<Object>();
    LatLng latLng;
    Context context;
    ArrayList<Integer> imageViewArrayList;
    int currentImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        context = this;

        // expandable listview data setup
        setGroupData();
        setChildGroupData();

        // Setting up Gallery Images
        imageViewArrayList = new ArrayList<Integer>();
        imageViewArrayList.add(R.drawable.splash);
        imageViewArrayList.add(R.drawable.main_header);
        imageViewArrayList.add(R.drawable.main_header_two);
        imageViewArrayList.add(R.drawable.common_google_signin_btn_icon_dark);
        imageViewArrayList.add(R.drawable.common_google_signin_btn_icon_dark_focused);
        imageViewArrayList.add(R.drawable.common_google_signin_btn_icon_dark_normal);
        imageViewArrayList.add(R.drawable.common_google_signin_btn_icon_dark_normal_background);
        imageViewArrayList.add(R.drawable.common_google_signin_btn_icon_disabled);
        imageViewArrayList.add(R.drawable.common_google_signin_btn_icon_light);
        imageViewArrayList.add(R.drawable.common_google_signin_btn_icon_light_focused);
        setGalleryImages(imageViewArrayList);

        // animate reserve button to grab attention
        animateReserveButton();

        // google maps setup
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // set up the expandableListView
        setupExpandableListView();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
    }

    // when map is setup, and view is opened, pin this location, zoom, and add marker
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        latLng = new LatLng(21.802820, 39.132578);

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
    }

    // initiator of expandable listview height
    public void checkExpandableListViewHeight(){

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
    //}

    // check permission for phone calls
    public boolean isPermissionGranted(){
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    // Gallery Images Handler
    public void clickHandler(View v){

        final Dialog dialog = new Dialog(RestaurantDetailsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.restaurant_details_gallery_activity);

        final ImageView imageView = dialog.findViewById(R.id.galleryImage);
        final SeekBar seekBar = dialog.findViewById(R.id.seekBar);
        seekBar.setEnabled(false);

        if(v.getTag().equals("imageOne")){

            imageView.setImageResource(imageViewArrayList.get(0));
            seekBar.setProgress(0);
            currentImage = 0;

        }else if(v.getTag().equals("imageTwo")){

            imageView.setImageResource(imageViewArrayList.get(1));
            seekBar.setProgress(1);
            currentImage = 1;

        }else if(v.getTag().equals("imageThree")){

            imageView.setImageResource(imageViewArrayList.get(2));
            seekBar.setProgress(2);
            currentImage = 2;

        }else {

            imageView.setImageResource(imageViewArrayList.get(3));
            seekBar.setProgress(3);
            currentImage = 3;

        }
        dialog.show();

        ImageView rightArrow = dialog.findViewById(R.id.rightArrowImage);
        ImageView leftArrow = dialog.findViewById(R.id.leftArrowImage);

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentImage < imageViewArrayList.size()-1){
                    currentImage++;
                    imageView.setImageResource(imageViewArrayList.get(currentImage));
                    seekBar.setProgress(currentImage);
                    System.out.println(currentImage);
                }else{
                    currentImage = 0;
                    imageView.setImageResource(imageViewArrayList.get(currentImage));
                    seekBar.setProgress(currentImage);
                    System.out.println(currentImage);
                }
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentImage > 0){
                    currentImage--;
                    imageView.setImageResource(imageViewArrayList.get(currentImage));
                    seekBar.setProgress(currentImage);
                }else{
                    currentImage = imageViewArrayList.size()-1;
                    imageView.setImageResource(imageViewArrayList.get(currentImage));
                    seekBar.setProgress(currentImage);
                }
            }
        });

    }

    // Set 4 displayed images
    public void setGalleryImages(ArrayList<Integer> imagesList){
        ImageView imageView1 = findViewById(R.id.galleryImageLeft);
        ImageView imageView2 = findViewById(R.id.galleryImageRight);
        ImageView imageView3 = findViewById(R.id.galleryImageBottomLeft);
        ImageView imageView4 = findViewById(R.id.galleryImageBottomRight);

        imageView1.setImageResource(imagesList.get(0));
        imageView2.setImageResource(imagesList.get(1));
        imageView3.setImageResource(imagesList.get(2));
        imageView4.setImageResource(imagesList.get(3));
    }

    // animate Reserve Button
    public void animateReserveButton(){
        final Button reserveButton = findViewById(R.id.reserveButton);
        reserveButton.animate().scaleXBy(0.2f).scaleYBy(0.2f).alpha(0.3f).setDuration(600);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                reserveButton.animate().scaleXBy(-0.2f).scaleYBy(-0.2f).alpha(1).setDuration(400);
            }
        }, 700);
    }

    // reservation button processing function
    public void processReservationButton(View v){
        context.startActivity(new Intent(context, RestaurantReservationActivity.class));
    }

    // expandable listview setup function
    public void setupExpandableListView(){
        // expandablelistview setup and adapter linking
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setFocusable(false);

        ExpandableListViewAdapter mNewAdapter = new ExpandableListViewAdapter(groupItem, childItem, expandableListView);
        mNewAdapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        expandableListView.setAdapter(mNewAdapter);

        // ENABLE NESTED SCROLLING OF EXPANDABLE LISTVIEW
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            expandableListView.setNestedScrollingEnabled(true);
        }

        // check expandable listview height before proceeding
        checkExpandableListViewHeight();
    }

    // call icon OnClick
    public void callIconProcess(View v){
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);

        builder.setTitle("0547171060").setMessage("Would you like to call this restaurant?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(isPermissionGranted()) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0547171060"));
                            context.startActivity(intent);
                        }else{
                            Toast.makeText(context,
                                    "Please enable phone permission through settings > apps & notifications",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_menu_call).show();
    }

    // location icon animation
    public void locationAnimation(View v){
        final ImageView locationImageView = findViewById(R.id.locationImage);
        locationImageView.clearAnimation();
        locationImageView.setRotation(360);
        locationImageView.animate().rotation(locationImageView.getRotation() + 360).setDuration(500);
    }

    // mapview on click processing function
    public void mapViewProcess(View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);

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
}
