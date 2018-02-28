package com.example.aelion.myapplicationandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        MyAT myAt = new MyAT();
        myAt.execute();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng toulouse = new LatLng(43.59999, 1.43333);
        mMap.addMarker(new MarkerOptions().position(toulouse).title("Toulouse"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(toulouse));

    }

    public class MyAT extends AsyncTask {

        ArrayList<StationBean> response;
        String url = "https://api.jcdecaux.com/vls/v1/stations?contract=Toulouse&apiKey=ec294a6634bdf8aa124b835d6c218ce8e2a8f42a";



        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                response = WSUtils.getStation();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            for (StationBean stationBean : response) {

                LatLng pos = new LatLng(stationBean.getPosition().getLat(), stationBean.getPosition().getLng());
                mMap.addMarker(new MarkerOptions().position(pos).title(stationBean.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));

            }

            Toast.makeText(MapsActivity.this, "Chargement terminé", Toast.LENGTH_SHORT).show();

        }
    }

}
