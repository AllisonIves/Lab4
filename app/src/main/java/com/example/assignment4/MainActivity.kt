package com.example.assignment4

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var adapter: LandmarkTypeAdapter? = null
    private var landmarkTypeList: MutableList<LandmarkType>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setLayoutManager(LinearLayoutManager(this))

        //initialize landmark types
        landmarkTypeList = ArrayList<LandmarkType>()
        landmarkTypeList!!.add(LandmarkType("Old Building"))
        landmarkTypeList!!.add(LandmarkType("Museum"))
        landmarkTypeList!!.add(LandmarkType("Stadium"))
        landmarkTypeList!!.add(LandmarkType("Attraction"))

        adapter =
            LandmarkTypeAdapter(landmarkTypeList as ArrayList<LandmarkType>) { type: LandmarkType ->
                this.onLandmarkTypeClicked(
                    type
                )
            }
        recyclerView.setAdapter(adapter)
        //initialize toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_home)

        toolbar.setNavigationOnClickListener {
            setContentView(R.layout.activity_main)
            finish()
        }
    }
//pass selected landmark type to landmark activity
    private fun onLandmarkTypeClicked(type: LandmarkType) {
        val intent = Intent(this, LandmarksActivity::class.java)
        intent.putExtra("landmarkTypeName", type.typeName)
        startActivity(intent)
    }
}
