package com.example.assignment4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment4.jsonUtils.loadLandmarksFromJson
import com.google.android.gms.maps.model.LatLng

class LandmarksActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LandmarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmarks)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //add nav button image
        toolbar.setNavigationIcon(R.drawable.ic_home)

        toolbar.setNavigationOnClickListener {
            setContentView(R.layout.activity_main)
            finish()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val typeNameTextView = findViewById<TextView>(R.id.typeNameTextView)

        //collect landmark type from intent
        val selectedType = intent.getStringExtra("landmarkTypeName") ?: "Unknown Type"
        typeNameTextView.text = selectedType


        val landmarkList = loadLandmarksFromJson(this)
        //filter landmarkList to only those of selected type
        val filteredLandmarks = landmarkList.filter {
            it.type?.typeName?.lowercase() == selectedType.lowercase()
        } as ArrayList
        Log.d("LandmarksActivity", "Filtered landmarks: $filteredLandmarks")

        adapter = LandmarkAdapter(filteredLandmarks) { landmark -> onLandmarkClicked(landmark) }
        recyclerView.adapter = adapter
    }
//pass to Map activity
    private fun onLandmarkClicked(landmark: Landmark) {
        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra("landmarkName", landmark.name)

        val landmarkLatLng = LatLng(landmark.latitude, landmark.longitude)
        intent.putExtra("landmarkLatLng", landmarkLatLng)
        startActivity(intent)
    }
}