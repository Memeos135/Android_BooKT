package com.bookt.bookt;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class A3_MapFragment extends Fragment {

    public A3_MapFragment() {
    }

    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.a3_map_view, container, false);

        mapView = v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        MapsInitializer.initialize(getActivity());

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                A2_RestaurantsActivityCard card = getActivity().getIntent().getParcelableExtra("restaurant_brief");
                // EXTRACT LOCATION COORDINATES FROM CARD OVER HERE, THEN SET IT IN LatLng
                if (card.getRestaurant_info().getRestaurant_location().length() > 100) {

                    A3_RestaurantDetailsActivity a3_restaurantDetailsActivity = (A3_RestaurantDetailsActivity) getActivity();
                    String[] x = a3_restaurantDetailsActivity.getIntent().getStringArrayExtra("location");

                    LatLng latLng = new LatLng(Double.parseDouble(x[0]), Double.parseDouble(x[1]));
                    googleMap.getUiSettings().setAllGesturesEnabled(false);
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14.0F);
                    googleMap.addMarker(new MarkerOptions().position(latLng)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                            .draggable(false).visible(true));
                    googleMap.moveCamera(cameraUpdate);

                }else{

                    Toast.makeText(getContext(), "Map is not working due to location format.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return v;
    }


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
