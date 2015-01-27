package com.example.andrew.comp3717project;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Andrew on 1/21/2015.
 */
public class LocationFragment extends Fragment{
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        // Inflate the layout for this fragment\
        return inflater.inflate( R.layout.fragment_location, container, false );
    }
}
