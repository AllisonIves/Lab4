package com.example.assignment4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//adapter for landmark
class LandmarkAdapter(
    private val landmarks: List<Landmark>,
    private val onItemClick: (Landmark) -> Unit
) : RecyclerView.Adapter<LandmarkAdapter.LandmarkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_landmark, parent, false)
        return LandmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: LandmarkViewHolder, position: Int) {
        val landmark = landmarks[position]
        holder.bind(landmark) //setting data with bind method
    }

    override fun getItemCount(): Int = landmarks.size

    inner class LandmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.landmarkName)
        private val addressTextView: TextView = itemView.findViewById(R.id.landmarkAddress)
//binding information to landmark from textviews
        fun bind(landmark: Landmark) {
            nameTextView.text = landmark.name
            addressTextView.text = landmark.address

            itemView.setOnClickListener {
                onItemClick(landmark)
            }
        }
    }
}