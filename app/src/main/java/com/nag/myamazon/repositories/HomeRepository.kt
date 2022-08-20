package com.nag.myamazon.repositories

import com.nag.myamazon.data.LocalData
import com.nag.myamazon.model.Mobile

class HomeRepository {

    fun getItemsData() : MutableList<Mobile>{
        //TODO: Network Call
        //     or
        //TODO: Local Data
        return LocalData.getData();


    }
}