package com.nag.myamazon.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mobile(val id:Int, val name:String, val brand:String, val price:Int, val image: String,
                  var quantity:Int = 0)  : Parcelable{
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}