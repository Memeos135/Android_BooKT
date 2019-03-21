package com.bookt.bookt;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
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
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class A3_RestaurantDetailsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    private ArrayList<Integer> imageViewArrayList;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a3_restaurant_details_activity);

        context = this;

        A2_RestaurantsActivityCard card = getIntent().getParcelableExtra("restaurant_brief");
        setupRestaurantCard(card);

        ratingBar = findViewById(R.id.ratingBar);
        Drawable drawable = ratingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#eb2748"), PorterDuff.Mode.SRC_ATOP);

        TextView seeAllReviews = findViewById(R.id.seeAll);
        seeAllReviews.setAnimation(new AnimationUtils().loadAnimation(context, R.anim.fui_slide_in_right));

        new A3_MapFragment();
        setupLocationInfo(card);

        // Setting up Gallery Images
        setupImageViewArrayList();

        // Setting up Header ViewPager
        setupImagesViewPager();

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

    // foodMenu image function
    public void foodMenu(View v){
        startActivity(new Intent(context, A5_FoodMenuActivity.class));
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
        context.startActivity(new Intent(context, A6_RestaurantReservationActivity.class));
    }


    // see all reviews text function process
    public void reviewsActivity(View v){
        startActivity(new Intent(context, A4_ReviewsActivity.class));
    }

    // call icon OnClick
    public void callIconProcess(View v){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);

        builder.setTitle("0547171060").setMessage("Would you like to call this restaurant?\n\nReservation through the application is preferred.")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(isPermissionGranted()) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0547171060"));
                            context.startActivity(intent);
                        }else{
                            Toast.makeText(context,
                                    "Please enable phone permission through settings > apps & notifications",
                                    Toast.LENGTH_LONG).show();
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

        imageViewArrayList.add(R.drawable.splash_screen);
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

                // DO NOTHING - must be implemented

            }

            @Override
            public void onPageScrollStateChanged(int state) {

                // DO NOTHING - must be implemented

            }
        });
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

    public void setupRestaurantCard(A2_RestaurantsActivityCard card){
        TextView restaurantName = findViewById(R.id.restaurantName);
        TextView openCloseHour = findViewById(R.id.openCloseHour);
        TextView restaurantCuisine = findViewById(R.id.resCuisine);
        TextView priceRange = findViewById(R.id.dollars);

        restaurantName.setText(card.getRestaurant_name());

        openCloseHour.setText(card.getRestaurant_open() + " - " + card.getRestaurant_close());
        restaurantCuisine.setText(card.getRestaurant_cuisine());

        colorPrice(priceRange, openCloseHour, card);

    }

    public void colorPrice(TextView restaurantPriceDollar, TextView openCloseHour, A2_RestaurantsActivityCard card){
        if(card.getRestaurant_price().equals("cheap")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else if(card.getRestaurant_price().equals("semi-moderate")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else if(card.getRestaurant_price().equals("moderate")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else if(card.getRestaurant_price().equals("semi-expensive")){

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, restaurantPriceDollar.getText().toString().length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }else{

            Spannable wordtoSpan = new SpannableString(restaurantPriceDollar.getText().toString());
            wordtoSpan.setSpan(new ForegroundColorSpan(restaurantPriceDollar.getResources().getColor(R.color.red_app)),
                    0, restaurantPriceDollar.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            restaurantPriceDollar.setText(wordtoSpan);

        }
    }

    public void setupLocationInfo(A2_RestaurantsActivityCard card) {

        TextView restaurantLocation = findViewById(R.id.restaurantLocation);
        // INSTEAD OF t, WE USE card OBJECT to get LOCATION and PARSE it.

        if (card.getRestaurant_location().length() > 100) {
            String latitude = card.getRestaurant_location().substring(card.getRestaurant_location().indexOf("@") + 1, (card.getRestaurant_location().indexOf("@") + 11));
            String longitude = card.getRestaurant_location().substring((card.getRestaurant_location().indexOf("@") + 12), ((card.getRestaurant_location().indexOf("@") + 22)));

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

//            String city = addresses.get(0).getLocality();
//            String state = addresses.get(0).getAdminArea();
//            String country = addresses.get(0).getCountryName();
//            String postalCode = addresses.get(0).getPostalCode();
//            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                restaurantLocation.setText(address);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            restaurantLocation.setText("Location format is wrong");
        }
    }
}
