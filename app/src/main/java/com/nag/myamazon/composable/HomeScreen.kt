@file:OptIn(ExperimentalMaterialApi::class)

package com.nag.myamazon.composable


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.nag.myamazon.R
import com.nag.myamazon.model.Mobile
import com.nag.myamazon.navigation.Screens
import com.nag.myamazon.viewmodel.MobilesSharedViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController, sharedViewModel: MobilesSharedViewModel) {

    val list = sharedViewModel.data

    Column(modifier = Modifier.fillMaxSize()) {

        appBar( navController, sharedViewModel)

        //TODO: Grid View
        LazyVerticalGrid(
            cells = GridCells.Fixed(2)) {
            items(list.size) { index ->
                mobilesGridItem(list.get(index),navController,sharedViewModel)
            }
        }
    }

}


@Composable
fun appBar(navController: NavController,sharedViewModel: MobilesSharedViewModel){

    var cartSize = remember {
        mutableStateOf(sharedViewModel.getListOfItems()?.size)
    }

    TopAppBar(
        title = { Text(text = "My Amazon") },
        actions = { IconButton(onClick = { navController.navigate(Screens.CartScreen.route)}) {
            Box( modifier = Modifier.fillMaxHeight().padding(5.dp),
               /* contentAlignment = Alignment.Center*/){

                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_to_cart),
                    "Cart", Modifier.align(Alignment.Center).padding(5.dp))

                if (cartSize.value!=0){
                    Text(text = "" + cartSize.value,color = Color.White,
                        modifier = Modifier.align(Alignment.TopEnd)
                        .drawBehind {
                            drawCircle(
                                color = Color.Red,
                            )
                        }
                        .size(18.dp)
                        .padding(1.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )

                }
            }


        } }
    )
}

@Composable
fun mobilesGridItem(mobile: Mobile, navController: NavController, sharedViewModel: MobilesSharedViewModel){
    Card(onClick = {

        sharedViewModel.setCartItem(mobile)
        navController.currentBackStackEntry?.savedStateHandle?.set(
            key = "mobile_item",
            value = mobile
        )
        navController.navigate(Screens.DetailsScreen.route)

    },
        modifier = Modifier.padding(2.dp)) {
        Column(
            verticalArrangement = Arrangement.Center,

            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .fillMaxWidth()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally


        ) {


            Image(
                painter = rememberAsyncImagePainter(model = mobile.image),
                contentDescription = mobile.name,
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)


            )

            Text(
                text = "$"+mobile.price,
                color = Color.Black,
                fontSize = 14.sp,
            )
            Text(
                text = mobile.name,
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
    }
}


