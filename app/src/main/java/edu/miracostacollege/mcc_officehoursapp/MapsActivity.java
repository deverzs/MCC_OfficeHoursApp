package edu.miracostacollege.mcc_officehoursapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.miracostacollege.mcc_officehoursapp.Model.DBHelper;
import edu.miracostacollege.mcc_officehoursapp.Model.Instructor;
import edu.miracostacollege.mcc_officehoursapp.Model.Schedule;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Instructor instructor;
    private Schedule schedule;
    private DBHelper db;
    private String officeHourLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        db = new DBHelper(this);

        instructor = getIntent().getParcelableExtra("SelectedInstructor");

        long id = instructor.getmId();
        schedule = db.getSchedule(id);

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
        officeHourLocation = schedule.getmOfficeHourLocation();

        LatLng miraCostaOC= new LatLng(33.190810, -117.302932);
        LatLng miraCostaSAN = new LatLng(33.017661, -117.258184);
        if(officeHourLocation.contains("OC")) {

            CameraPosition cameraPosition = new CameraPosition.Builder().target(miraCostaOC).zoom(15f).build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mMap.addMarker(new MarkerOptions().position(miraCostaOC).title("MiraCosta College Oceanside"));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(miraCostaOC));
            mMap.moveCamera(update);

        }
        else if(officeHourLocation.contains("SAN"))
        {
            CameraPosition cameraPosition = new CameraPosition.Builder().target(miraCostaSAN).zoom(15f).build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mMap.addMarker(new MarkerOptions().position(miraCostaSAN).title("MiraCosta College San Elijo"));
            mMap.moveCamera(update);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(miraCostaSAN));

        }
        else
        {
            Toast.makeText(this, "Office hours are online", Toast.LENGTH_LONG).show();
        }
    }
}