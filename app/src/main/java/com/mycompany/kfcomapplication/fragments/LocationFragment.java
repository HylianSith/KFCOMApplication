package com.mycompany.kfcomapplication.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mycompany.kfcomapplication.R;
import com.mycompany.kfcomapplication.models.Pin;
import com.mycompany.kfcomapplication.utils.PinsApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.google.android.gms.analytics.internal.zzy.t;

/**
 * Created by jason_000 on 11/29/2016.
 */

public class LocationFragment extends SupportMapFragment implements OnMapReadyCallback {

    public static LocationFragment getInstance() {
        LocationFragment fragment = new LocationFragment();

        return fragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        CameraPosition position = CameraPosition.builder()
                .target(new LatLng(42.921966,  -85.718533))
                .zoom( 6f )
                .bearing( 0.0f )
                .tilt( 0.0f )
                .build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.setTrafficEnabled(true);

        googleMap.getUiSettings().setZoomControlsEnabled(true);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return true;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/anonymous/e83c4a7e0e0f6e0afdd1f545ed6d9351/raw/8cd52419a47711a725b6fc0c665f5ff88c6e968a/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PinsApiInterface pinsApiInterface = retrofit.create(PinsApiInterface.class);
        Call<List<Pin>> myCall = pinsApiInterface.getStreams();
        myCall.enqueue(new Callback<List<Pin>>() {
            @Override
            public void onResponse(Call<List<Pin>> pins, Response<List<Pin>> response) {
                if(!isAdded() || pins == null || pins.equals("") )
                {
                    return;
                }

                for( Pin pin : response.body()) {
                    MarkerOptions options = new MarkerOptions().position( new LatLng(pin.getLatitude(), pin.getLongitude()));
                    options.title(pin.getName());
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    googleMap.addMarker(options);
                }
            }

            @Override
            public void onFailure(Call<List<Pin>> call, Throwable t) {

            }
        });
    }
}
