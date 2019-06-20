package com.example.aidlservices

import android.os.Parcel
import android.os.Parcelable

class Num(private var a : Int, private var b : Int) : Parcelable{


//    private var a : Int = a
//    private val b : Int = b

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel?.writeInt(a)
        parcel?.writeInt(b)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Num> {
        override fun createFromParcel(parcel: Parcel): Num {
            return Num(parcel)
        }

        override fun newArray(size: Int): Array<Num?> {
            return arrayOfNulls(size)
        }
    }

    fun add():Int {
        return a + b
    }


}