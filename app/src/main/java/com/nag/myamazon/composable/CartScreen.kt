@file:OptIn(ExperimentalMaterialApi::class)

package com.nag.myamazon.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.nag.myamazon.R
import com.nag.myamazon.model.Mobile
import com.nag.myamazon.viewmodel.MobilesSharedViewModel

@Composable
fun CartScreen(navController: NavController,sharedViewModel: MobilesSharedViewModel) {

    var list = sharedViewModel.getListOfItems()
    val mContext = LocalContext.current

    var totalAmount: MutableState<Int> = remember {
        mutableStateOf(
            sharedViewModel.getListOfItems().sumOf { (it.price * it.quantity) }.toInt()
        )
    }

    Column {

        TopAppBar(title = { Text(text = "Cart") }, navigationIcon = { IconButton(onClick = {navController.popBackStack()}) { Icon(Icons.Filled.ArrowBack, contentDescription = "Back",Modifier.padding(15.dp)) } })

        Column(modifier = Modifier.weight(9f)) {
            cartItemsView(mContext,navController,list,sharedViewModel,totalAmount)
        }

        placeOrderView(mContext,totalAmount)

    }

}

@Composable
fun cartItemsView(mContext: Context,navController: NavController,
                  list: MutableSet<Mobile>,
                  sharedViewModel: MobilesSharedViewModel, totalAmount: MutableState<Int>) {
    if (list.size==0) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Your Cart is Empty")
            Spacer(modifier = Modifier.height(15.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Shop Now")
            }
        }
    }

    if (list.size>0){
        LazyColumn(modifier = Modifier
            .fillMaxWidth() ){


            items(list.size){ index ->

                var quantity : MutableState<Int> = remember {
                    mutableStateOf(
                        (list.elementAt(index))?.quantity
                    )
                }





                Card(modifier = Modifier.fillMaxWidth().padding(2.dp),
                    elevation = 5.dp,
                    onClick = {

                    }) {

                    Row(horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = rememberAsyncImagePainter(model = list.elementAt(index).image),
                            contentDescription = list.elementAt(index).name,
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                                .weight(2f).padding(5.dp)
                        )

                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .weight(4f),
                            horizontalAlignment = Alignment.Start,
//                            verticalArrangement = Arrangement.Center

                        ) {

                            Text(
                                text = list.elementAt(index).brand+" - "+list.elementAt(index).name,
                                color = Color.Black,
                                fontSize = 14.sp,
                            )
                            Text(
                                text = "$"+list.elementAt(index).price,
                                color = Color.Black,
                                fontSize = 14.sp,
                            )
                        }

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .weight(4f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center) {

                            IconButton(onClick = {
                                var item = list.elementAt(index)
                                if (item.quantity>1) {
                                    item.quantity -= 1
                                    sharedViewModel.addItemToCart(item)
                                    quantity.value -= 1

                                    totalAmount.value = sharedViewModel.getListOfItems().sumOf { (it.price * it.quantity) }.toInt()
                                }

                                if(item.quantity==1){
                                    Toast.makeText(mContext,"Remove Item?",Toast.LENGTH_LONG).show()
                                }



                            }) {
                                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_minus
                                ),"Cart",Modifier.size(18.dp) )

                            }

//                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text =""+quantity.value,
                                color = Color.Black,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(5.dp)
                            )
//                            Spacer(modifier = Modifier.width(2.dp))

                            IconButton(onClick = {
                                var item =list.elementAt(index)
                                item.quantity+=1
                                sharedViewModel.addItemToCart(item)
                                quantity.value += 1
                                totalAmount.value = sharedViewModel.getListOfItems().sumOf { (it.price * it.quantity) }.toInt()
//                                    Toast.makeText(mContext,"Plus Clicked", Toast.LENGTH_LONG).show()

                            }) {
                                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus
                                ),"Cart",Modifier.size(18.dp) )
                            }


                        }


                    }

                }

            }
        }
    }
}

@Composable
fun placeOrderView(mContext: Context, totalAmount: MutableState<Int>) {
    BottomAppBar( backgroundColor = Color.White) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "Total : $ " + totalAmount.value,
                color = Color.Black,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = {

                Toast.makeText(mContext,"Order Placed", Toast.LENGTH_LONG).show()

            },modifier = Modifier
                .fillMaxWidth()
                .weight(1f), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                Text(text = "Place Order", color = Color.White)

            }
        }
    }
}






