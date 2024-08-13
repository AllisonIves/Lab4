package com.example.assignment4

import android.os.Parcel
import android.os.Parcelable
//class for landmark
data class Landmark(
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val type: LandmarkType?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readParcelable(LandmarkType::class.java.classLoader) //I know parcelable is deprecated but I kept breaking things when I tried to avoid using it
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeParcelable(type, flags)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Landmark> {
        override fun createFromParcel(parcel: Parcel): Landmark {
            return Landmark(parcel)
        }

        override fun newArray(size: Int): Array<Landmark?> {
            return arrayOfNulls(size)
        }
    }
}