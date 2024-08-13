package com.example.assignment4

import android.content.Context
import org.json.JSONArray
//create a util to load the json file
object jsonUtils {

    fun loadLandmarksFromJson(context: Context): ArrayList<Landmark> {
        val inputStream = context.resources.openRawResource(R.raw.landmarks)
        val json = inputStream.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(json)
        val landmarks = ArrayList<Landmark>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val name = jsonObject.getString("name")
            val address = jsonObject.getString("address")
            val latitude = jsonObject.getDouble("latitude")
            val longitude = jsonObject.getDouble("longitude")
            val typeName = jsonObject.getString("typeName")

            val landmarkType = LandmarkType(typeName)
            landmarks.add(Landmark(name, address, latitude, longitude, landmarkType))
        }

        return landmarks
    }
}