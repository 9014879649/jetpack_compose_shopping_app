@file:OptIn(ExperimentalMaterialApi::class)

package com.nag.myamazon.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.nag.myamazon.R
import com.nag.myamazon.model.Mobile
import com.nag.myamazon.navigation.Screens
import com.nag.myamazon.viewmodel.MobilesSharedViewModel

@Composable
fun DetailsScreen(navController: NavController, sharedViewModel: MobilesSharedViewModel) {
    val mContext = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        titleBar(navController, sharedViewModel)

        Column( modifier = Modifier.weight(9f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            val mobile = sharedViewModel.getCartItem();
            mobile?.let { itemInfo(it) }
        }

        addOrViewCart(navController, sharedViewModel,mContext)
    }
}

@Composable
fun itemInfo(mobile: Mobile){
    val  itemDesc  = stringResource(id = R.string.item_desc)
    Card(onClick = {  }) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = rememberAsyncImagePainter(model = mobile.image),
                contentDescription = mobile.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(15.dp),
            )
            Spacer(modifier = Modifier.size(15.dp))
            Text(text = "$ "+mobile.price,  color = Color.Red, fontSize = 20.sp)
            Text(text = ""+mobile.brand+" - "+mobile.name, color = Color.Black, fontSize = 18.sp)

            Spacer(modifier = Modifier.size(15.dp))


            Text( fontSize = 16.sp, color = Color.Gray, text = itemDesc)

        }



    }
}


@Composable
fun titleBar(navController: NavController,sharedViewModel: MobilesSharedViewModel){
    TopAppBar(
        title = { Text(text = ""+sharedViewModel.getCartItem()?.brand) },
        navigationIcon = {
            IconButton(onClick = {navController.popBackStack()}) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back",Modifier.padding(15.dp))
            }

        }
    )
}


@Composable
fun addOrViewCart(
    navController: NavController,
    sharedViewModel: MobilesSharedViewModel,
    mContext: Context
){
    var item:Mobile? = null

    BottomAppBar( backgroundColor = Color.White) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(onClick = {
                item = sharedViewModel.getCartItem()
                item!!.quantity = 1

                sharedViewModel?.addItemToCart(item!!)
                Toast.makeText(mContext,"Item Added!",Toast.LENGTH_LONG).show()

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                Text(text = "Add To Cart", color = Color.White)

            }

            Spacer(modifier = Modifier.width(5.dp))

            Button(onClick = { navController.navigate(Screens.CartScreen.route) },modifier = Modifier
                .fillMaxWidth()
                .weight(1f), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                Text(text = "View Cart", color = Color.White)
            }
        }
    }
}