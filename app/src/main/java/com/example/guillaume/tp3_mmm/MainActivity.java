package com.example.guillaume.tp3_mmm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.guillaume.tp3_mmm.fragments.DetailFragment;
import com.example.guillaume.tp3_mmm.fragments.RegionListFragment;
import com.example.guillaume.tp3_mmm.model.Region;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements RegionListFragment.OnListFragmentInteractionListener, DetailFragment.OnDetailFragmentInteractionListener, OnMapReadyCallback {

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.frame_region_list) != null) {
            if (savedInstanceState != null) {
                return;
            }

            RegionListFragment regionListFragment = new RegionListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_region_list, regionListFragment).commit();
        }
    }

    @Override
    public void onListFragmentInteraction(Region item) {
        DetailFragment detailFragment = DetailFragment.newInstance(item);

        if (findViewById(R.id.frame_region_detail) != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_region_detail, detailFragment).addToBackStack(null).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_region_list, detailFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onDetailFragmentInteraction(double lat, double lng) {
        latitude = lat;
        longitude = lng;
        openMapFragment();
    }

    private void openMapFragment() {
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);

        if (findViewById(R.id.frame_region_detail) != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_region_detail, mapFragment).addToBackStack(null).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_region_list, mapFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney and move the camera
        LatLng destination = new LatLng(-34, 151); //Sydney

        if (latitude != 0 && longitude != 0) {
            destination = new LatLng(latitude, longitude); //Region
        }

        googleMap.addMarker(new MarkerOptions().position(destination).title("Marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 6));
    }
}
