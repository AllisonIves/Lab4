package com.example.assignment4

import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class LandmarkTypeAdapter(
    private val landmarkTypes: List<LandmarkType>,
    private val onLandmarkTypeClick: (LandmarkType) -> Unit
) : RecyclerView.Adapter<LandmarkTypeAdapter.LandmarkTypeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_landmarktype, parent, false)
        return LandmarkTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: LandmarkTypeViewHolder, position: Int) {
        val landmarkType = landmarkTypes[position]
        holder.bind(landmarkType)
    }

    override fun getItemCount(): Int = landmarkTypes.size

    inner class LandmarkTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val typeNameTextView: TextView = itemView.findViewById(R.id.typeNameTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val landmarkType = landmarkTypes[position]
                    onLandmarkTypeClick(landmarkType)
                }
            }
        }

        fun bind(landmarkType: LandmarkType) {
            typeNameTextView.text = landmarkType.typeName
        }
    }
}