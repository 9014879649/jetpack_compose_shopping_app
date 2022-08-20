package com.nag.myamazon.viewmodel

import android.graphics.Insets.add
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nag.myamazon.model.Mobile

class MobilesSharedViewModel : ViewModel() {

//    private var mobilesList = mutableListOf<Mobile>()

    private var mobilesList = mutableSetOf<Mobile>()


    private var mobile:Mobile? = null

    fun setCartItem(item: Mobile){
        mobile = item;
    }

    fun getCartItem(): Mobile? {
        return mobile;
    }

    fun getListOfItems(): MutableSet<Mobile> {
        return mobilesList;
    }


    fun addItemToCart( mobile: Mobile){
        mobilesList.add(mobile)

    }

    fun removeCartItem(mobile: Mobile){
        mobilesList.forEachIndexed{index,element ->
            if (element.id.equals(mobile.id)){
                mobilesList.remove(mobile)
            }

        }

    }




}