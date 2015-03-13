package com.example.andrew.comp3717project;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.*;
import com.google.android.gms.drive.*;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Andrew on 1/21/2015.
 */
public class LocationFragment extends Fragment implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {

        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // Inflate the layout for this fragment\
        return inflater.inflate( R.layout.fragment_location, container, false );


    }

/*
    public void onCreate(Bundle savedInstanceState) {
       // mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
       // mapFragment.getMapAsync(this);

    }*/

    @Override
    public void onMapReady(GoogleMap map) {
        /*
        Marker perth = map.addMarker(new MarkerOptions()
                .position(new LatLng(-39.90,115.86))
                .title("Marker"));*/
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-31.90, 115.86),12));
        Marker test = map.addMarker(new MarkerOptions().position(new LatLng(-31.90, 115.86)).title("helloworld"));
    }
}
