package com.nag.myamazon.utill

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.nag.myamazon.R

sealed class MenuAction(@StringRes val label:Int,@DrawableRes val icon:Int){
    object Cart : MenuAction(R.string.cart,android.R.drawable.ic_menu_share)
}
