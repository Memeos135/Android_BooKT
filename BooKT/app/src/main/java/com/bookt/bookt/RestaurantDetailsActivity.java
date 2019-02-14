package com.bookt.bookt;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class RestaurantDetailsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView expandableRecyclerView;
    private Context context;
    private ArrayList<Integer> imageViewArrayList;
    private int currentImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        context = this;

//        // Setup ExpandableListView
//        setupExpandableListView();

        new MapFragment();

        // Setting up Gallery Images
        setupImageViewArrayList();
        //setGalleryImages(imageViewArrayList);

        // Setting up Header ViewPager
        setupImagesViewPager();

        // Setting up Expandable Recycler View
        setupExpandableRecyclerView();

        // animate reserve button to grab attention
        animateReserveButton();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
            context.startActivity(new Intent(context, GalleryActivity.class));
        } else if (id == R.id.Profile) {

        } else if (id == R.id.Reservations) {

        } else if (id == R.id.History) {

        } else if (id == R.id.Signup) {

        } else if (id == R.id.Signin) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // check permission for phone calls
    private boolean isPermissionGranted(){
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    // animate Reserve Button
    private void animateReserveButton(){
        final Button reserveButton = findViewById(R.id.reserveButton);
        reserveButton.animate().scaleXBy(0.2f).scaleYBy(0.2f).alpha(0.3f).setDuration(600L);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                reserveButton.animate().scaleXBy(-0.2f).scaleYBy(-0.2f).alpha(1.0F).setDuration(400L);
            }
        }, 700L);
    }

    // reservation button processing function
    public void processReservationButton(View v){
        context.startActivity(new Intent(context, RestaurantReservationActivity.class));
    }

    // call icon OnClick
    public void callIconProcess(View v){
        AlertDialog.Builder builder =
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
        ImageView locationImageView = findViewById(R.id.locationImage);
        locationImageView.clearAnimation();
        locationImageView.setRotation(360.0F);
        locationImageView.animate().rotation(locationImageView.getRotation() + 360.0F).setDuration(500L);
    }

    // Gallery Images Adapter
    private class ViewPagerImageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViewArrayList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == ((ImageView) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imageViewArrayList.get(position));
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }

    // Image ArrayList Setup
    public void setupImageViewArrayList(){

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
    }

    // ViewPager Setup
    public void setupImagesViewPager() {

        if (imageViewArrayList.size() == 0) {

            imageViewArrayList.add(R.drawable.icon);

        }
        ViewPager viewPager = findViewById(R.id.viewPager);

        ViewPagerImageAdapter viewPagerImageAdapter = new ViewPagerImageAdapter();
        viewPager.setAdapter(viewPagerImageAdapter);

        viewPager.setOffscreenPageLimit(2);

        final SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setEnabled(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                seekBar.setProgress(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setupExpandableRecyclerView(){

        expandableRecyclerView = findViewById(R.id.expandableRecyclerView);

        ArrayList<ExpandableGroupItem> expandableGroupItem = new ArrayList<ExpandableGroupItem>();
        ArrayList<ExpandableChildItem> expandableChildItem = new ArrayList<ExpandableChildItem>();

        expandableChildItem.add(new ExpandableChildItem("Cheese Burgers", "Double patty with cheese", "", "6$"));
        expandableChildItem.add(new ExpandableChildItem("Cheese Burgers", "Double patty with cheese", "", "6$"));
        expandableChildItem.add(new ExpandableChildItem("Cheese Burgers", "Double patty with cheese", "", "6$"));

        expandableGroupItem.add(new ExpandableGroupItem("Starters", expandableChildItem));
        expandableGroupItem.add(new ExpandableGroupItem("Starters", expandableChildItem));
        expandableGroupItem.add(new ExpandableGroupItem("Starters", expandableChildItem));
        expandableGroupItem.add(new ExpandableGroupItem("Starters", expandableChildItem));
        expandableGroupItem.add(new ExpandableGroupItem("Starters", expandableChildItem));

        ExpandableRecyclerAdapter expandableRecyclerAdapter = new ExpandableRecyclerAdapter(expandableGroupItem, context);
        expandableRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        expandableRecyclerView.setAdapter(expandableRecyclerAdapter);
        expandableRecyclerView.setNestedScrollingEnabled(false);
    }
}
