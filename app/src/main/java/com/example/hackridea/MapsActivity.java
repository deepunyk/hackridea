package com.example.hackridea;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng market = new LatLng( 13.206982, 74.908139);
        LatLng sydne2 = new LatLng(13.210010233906523, 74.93885963063424);
        LatLng market2 = new LatLng( 13.213061810204598, 74.993324);
        LatLng market3 = new LatLng( 13.143976 , 74.999518);
        LatLng dairy = new LatLng( 13.165782, 74.869879);
        LatLng dairy2 = new LatLng(  13.213603, 74.872495);
        LatLng bank = new LatLng(  13.192957 , 74.967369);
        LatLng bank2 = new LatLng( 13.074309 , 74.999827);
        LatLng Store = new LatLng( 13.22865,  75.057224);
        LatLng Store1 = new LatLng( 13.177097, 74.986952);
        LatLng Krishie = new LatLng( 13.338758, 74.743818);

        float zoomLevel = 10.0f; //This goes up to 21
//        mGoogleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mGoogleMap.addMarker(new MarkerOptions().position(sydne2).title("Marker in new"));

//        mMap.addMarker(new MarkerOptions().position(market).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.user)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydne2,zoomLevel));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.user);
        LatLng bangalore = new LatLng(12.9716, 77.5946);

        int height = 100;
        int width = 100;
        String su="user";

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.store);
        mapicon(b,market,"Nitte Krishi Store","Store");

        Bitmap c = BitmapFactory.decodeResource(getResources(), R.drawable.government);
        mapicon(c,sydne2,"Nitte Krishi Society","Society");

        Bitmap d = BitmapFactory.decodeResource(getResources(), R.drawable.fruit);
        mapicon(d,market2,"Nitte Krishi Market","Market place");

        Bitmap e = BitmapFactory.decodeResource(getResources(), R.drawable.fruit);
        mapicon(e,market3,"Krishi Market","Market place");

        Bitmap f = BitmapFactory.decodeResource(getResources(), R.drawable.dairyproducts);
        mapicon(f,dairy,"Nitte Milk Dairy","Dairy");

        Bitmap g = BitmapFactory.decodeResource(getResources(), R.drawable.dairyproducts);
        mapicon(g,dairy2,"Milk Dairy","Dairy");

        Bitmap h = BitmapFactory.decodeResource(getResources(), R.drawable.bank);
        mapicon(h,bank,"Nitte Krishi Bank","Bank");

        Bitmap i = BitmapFactory.decodeResource(getResources(), R.drawable.government);
        mapicon(i,bank2,"Nitte Krishi Bank","Bank");

        Bitmap j = BitmapFactory.decodeResource(getResources(), R.drawable.agriequip);
        mapicon(j,Store,"Nitte Krishi Store","Equipment Store");

        Bitmap k = BitmapFactory.decodeResource(getResources(), R.drawable.agriculture);
        mapicon(k,Store1,"Tractor Rentals","Store");

        Bitmap l = BitmapFactory.decodeResource(getResources(), R.drawable.government);
        mapicon(l,Krishie,"Udupi Agricultural Deparment","Government");
//        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
//        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);
//
//
//        MarkerOptions markerOptions = new MarkerOptions().position(bangalore)
//                .title("Current Location")
//                .snippet("hello").icon(smallMarkerIcon);
//
//
//        mMap.addMarker(markerOptions);
//        LatLng MELBOURNE = new LatLng(-37.813, 144.962);
//        Marker melbourne = mMap.addMarker(new MarkerOptions()
//                .position(MELBOURNE)
//                .title("Melbourne")
//                .snippet("Population: 4,137,400")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_arrow_back_black_24dp)));


    }
    public void mapicon(Bitmap bmap,LatLng mapnew,String title,String snippet)
    {
        int height = 100;
        int width = 100;
        Bitmap smallMarker = Bitmap.createScaledBitmap(bmap, width, height, false);
        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);


        MarkerOptions markerOptions = new MarkerOptions().position(mapnew)
                .title(title)
                .snippet(snippet).icon(smallMarkerIcon);


        mMap.addMarker(markerOptions);

    }

}
