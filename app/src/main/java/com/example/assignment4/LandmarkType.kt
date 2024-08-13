package com.example.assignment4

import android.os.Parcel
import android.os.Parcelable

data class LandmarkType(
    val typeName: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(typeName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LandmarkType> {
        override fun createFromParcel(parcel: Parcel): LandmarkType {
            return LandmarkType(parcel)
        }

        override fun newArray(size: Int): Array<LandmarkType?> {
            return arrayOfNulls(size)
        }
    }
}