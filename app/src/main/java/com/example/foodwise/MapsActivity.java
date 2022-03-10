package com.example.foodwise;

import androidx.fragment.app.FragmentActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.foodwise.databinding.ActivityMapsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private Marker marker;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        new AsyncTaskGetMareker().execute();
    }

    public String getJSONFromAssets() {
        String json = null;
        try {
            InputStream inputData = getAssets().open("foodbanks.json");
            int size = inputData.available();
            byte[] buffer = new byte[size];
            inputData.read(buffer);
            inputData.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private class AsyncTaskGetMareker extends AsyncTask<String , String, JSONArray> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONArray doInBackground(String... strings) {
            String stationsJsonString = getJSONFromAssets();
            try {
                JSONArray stationsJsonArray = new JSONArray(stationsJsonString);
                return stationsJsonArray;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //This will only happen if an exception is thrown above:
            return null;
        }

        protected void onPostExecute (JSONArray result){
            if (result !=null){
                for (int i =0; i <result.length(); i++){
                    JSONObject jsonObject= null;
                    try {
                        jsonObject= result.getJSONObject(i);
                        String name=jsonObject.getString("name");
                        String lat=jsonObject.getString("lat");
                        String lang=jsonObject.getString("lang");
                        String address=jsonObject.getString("address");
                        String phone=jsonObject.getString("phone");
                        String contact = address + "\n" + phone;

                        drawMarker(new LatLng(Double.parseDouble(lat),
                                Double.parseDouble(lang)), name, contact);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        private void drawMarker(LatLng point, String name,String contact) {
            LatLng loc = new LatLng(49.2497769,
                    -122.9248886);
//            MarkerOptions markerOptions = new MarkerOptions() .position(point)
//                                       .title(name)
//                                       .snippet(contact);
            //markerOptions.position(point);
            //markerOptions.snippet(name);
            // mMap.addMarker(markerOptions);
            mMap.addMarker(
                    new MarkerOptions()
                            .position(point)
                            .title(name)
                            .snippet(contact));

            //mMap.addMarker(new MarkerOptions().position(point).title(name));
            float zoomLevel = (float) 9.5;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, zoomLevel));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }
    }



}
//
//import androidx.fragment.app.FragmentActivity;
//
//import android.os.Bundle;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.example.foodwise.databinding.ActivityMapsBinding;
//
//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
//
//    private GoogleMap mMap;
//    private ActivityMapsBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMapsBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
//}