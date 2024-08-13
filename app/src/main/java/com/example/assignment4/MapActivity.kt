package com.example.assignment4

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_home)

        toolbar.setNavigationOnClickListener {
            setContentView(R.layout.activity_main)
            finish()
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val landmarkName = intent.getStringExtra("landmarkName") ?: "Unknown Landmark"
        val landmarkLatLng = intent.getParcelableExtra<LatLng>("landmarkLatLng")
//I know this is deprecated, I am working on figuring out the alternatives

        //if coordinates aren't null, add a marker and move to the coordinates
        if (landmarkLatLng != null) {
            mMap.addMarker(MarkerOptions().position(landmarkLatLng).title(landmarkName))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(landmarkLatLng, 15f))
        } else {
            //otherwise, toast warning
            Toast.makeText(this, "No location of this type is available", Toast.LENGTH_SHORT).show()
        }
    }

}