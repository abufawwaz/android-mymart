package com.example.mymart;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import model.LatitudeLongitude;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<LatitudeLongitude> longitudeList=new ArrayList<>();
        longitudeList.add(new LatitudeLongitude(27.7052354,85.3294158,"MyMart,Dillibazar"));
        longitudeList.add(new LatitudeLongitude(27.7064748,85.3142013,"MyMart,DurbarMarg"));
        longitudeList.add(new LatitudeLongitude(27.7415528,85.3346216,"MyMart,Bansbari"));
        longitudeList.add(new LatitudeLongitude(27.6856712,85.299826,"MyMart,sanepa"));

        CameraUpdate center,zoom;

        for (int i=0;i<longitudeList.size();i++){
            center = CameraUpdateFactory.newLatLng(new LatLng(longitudeList.get(i).getLat(),
                    longitudeList.get(i).getLon()));
            zoom = CameraUpdateFactory.zoomTo(13);

            mMap.addMarker(new MarkerOptions().position(new LatLng(longitudeList.get(i).getLat(),
                    longitudeList.get(i).getLon())).title(longitudeList.get(i).getMarker()));

            mMap.moveCamera(center);

            mMap.animateCamera(zoom);

            mMap.setMapType(mMap.MAP_TYPE_HYBRID);

            mMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }
}
