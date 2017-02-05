package com.example.guillaume.tp3_mmm.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.example.guillaume.tp3_mmm.MapsActivity;
import com.example.guillaume.tp3_mmm.R;
import com.example.guillaume.tp3_mmm.model.Command;
import com.example.guillaume.tp3_mmm.model.Region;
import com.example.guillaume.tp3_mmm.util.GeocodeJSONRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment implements OnMapReadyCallback {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_REGION = "region";

    Button btn_locate;

    private Region region;
    private double longitude;
    private double latitude;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param region Region
     * @return A new instance of fragment DetailFragment.
     */
    public static DetailFragment newInstance(Region region) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_REGION, region);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            region = getArguments().getParcelable(ARG_REGION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detail, container, false);

        loadURL(view);

        btn_locate = (Button) view.findViewById(R.id.btn_locate);
        btn_locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapFragment(view);
            }
        });

        return view;
    }

    private void openMapFragment(View view) {
        if (view.findViewById(R.id.frame_region_detail) != null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_region_detail, mapFragment).addToBackStack(null).commit();
        } else {
            Intent i = new Intent(getContext(), MapsActivity.class);
            i.putExtra("latitude", latitude);
            i.putExtra("longitude", longitude);
            startActivity(i);
        }
    }

    private void loadURL(View view) {

        WebView webview = (WebView) view.findViewById(R.id.webview_details);

        String url;
        if (region != null) {
            url = region.getUrl();
        } else {
            url = getResources().getString(R.string.default_url);
        }

        webview.loadUrl(url);


        //Calculate GPS for the region

        String location = "France";
        if (region != null) {
            location = region.getName();
        }

        String query;
        try {
            query = URLEncoder.encode("France Region " + location, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }

        String geoCoderUrl = String.format(
                "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s",
                query,
                "AIzaSyD2r8tLECJlL8Lj2yqkD72GiOl1to4lA7I");

        final GeocodeJSONRequest task = new GeocodeJSONRequest(geoCoderUrl);
        task.setCmdUpdateLocation(new Command() {
            @Override
            public void execute() {
                latitude = task.getLat();
                longitude = task.getLng();
                Log.e("DetailFragment","Latitude = "+latitude);
                Log.e("DetailFragment","Longitude = "+longitude);
                btn_locate.setEnabled(true);
            }
        });
        task.execute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney and move the camera
        LatLng destination = new LatLng(-34, 151); //Sydney

        if(latitude != 0 && longitude != 0){
            destination = new LatLng(latitude, longitude); //Region
        }

        googleMap.addMarker(new MarkerOptions().position(destination).title("Marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(destination));
    }
}
