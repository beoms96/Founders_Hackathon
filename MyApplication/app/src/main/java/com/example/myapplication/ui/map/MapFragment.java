package com.example.myapplication.ui.map;

import android.content.Context;
import android.graphics.Camera;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;



public class MapFragment extends Fragment {
//    private GoogleMap mMap;
//    private CameraPosition mCameraPosition;
//
//    private GeoDataClient mGeoDataClient;
//    private PlaceDetectionClient mPlaceDetectionClient;
//
//    private final LatLng mDefaultLocation = new LatLng(35.05148245, 126.72306776);
//    private static final int DEFAULT_ZOOM = 15;
//    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION =1;
//    private boolean mLocationPermissionGranted;
//    private Location mLastKnownLocation;
//
//    private static final String KEY_CAMERA_POSITION="camera_position";
//    private static final String KEY_LOCATION = "location";
    public MapFragment(){

    }

    private MapViewModel mapViewModel;
    private static View root;
    private SupportMapFragment mapFragment;
    static View v; // 프래그먼트의 뷰 인스턴스
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(v!=null){
            ViewGroup parent = (ViewGroup)v.getParent();
            if(parent!=null){
                parent.removeView(v);
            }
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mapViewModel =
                ViewModelProviders.of(this).get(MapViewModel.class);
        try{
            root = inflater.inflate(R.layout.fragment_map, container, false);

        }catch (InflateException e){

        }

        mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear();
                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(37.498109,127.027698))
                        .zoom(15)
                        .bearing(0)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex),10000,null);


            }
        });


        Places.initialize(getActivity().getApplicationContext(),"AIzaSyCkgjQH_EQd9KkFzvh6Q8y3TqFQ0pVOD2Y");

        if (!Places.isInitialized()) {
            Places.initialize(getActivity().getApplicationContext(), "AIzaSyCkgjQH_EQd9KkFzvh6Q8y3TqFQ0pVOD2Y");
        }
//
//        PlaceAutocompleteFragment autocompleteFragment=(PlaceAutocompleteFragment)
//                getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                Location location=new Location("");
//                location.setLatitude(place.getLatLng().latitude);
//                location.setLongitude(place.getLatLng().longitude);
//
////                setCurrentLocation(location,place.getName().toString(),place.getAddress().toString());
//            }
//
//            @Override
//            public void onError(Status status) {
//                Log.d("Map","An error occurred: "+ status);
//            }
//        });


        return root;
    }
}