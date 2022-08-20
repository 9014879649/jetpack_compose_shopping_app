@file:OptIn(ExperimentalMaterialApi::class)

package com.nag.myamazon.composable

//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import android.util.Log
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

    val list = getData()


    var size = remember {
        mutableStateOf(sharedViewModel.getListOfItems()?.size)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = "My Amazon") },

            actions = { IconButton(onClick = { navController.navigate(Screens.CartScreen.route)}) {
                Box( modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center){

                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_to_cart),"Cart",Modifier.size(40.dp) )



                    if (size.value!=0){
                        Row( verticalAlignment = Alignment.Top, ) {
                            Spacer(modifier = Modifier.width(20.dp))

                            Text(text = "" + size.value,color = Color.White, modifier = Modifier
                                .drawBehind {
                                    drawCircle(
                                        color = Color.Red,
                                    )
                                }
                                .size(24.dp)
                                .padding(1.dp),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }



                }


            } }
        )

        LazyVerticalGrid(cells = GridCells.Fixed(2)) {


            items(list.size) { index ->
                Card(onClick = {
                    val mobile =  list.get(index);
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
                            painter = rememberAsyncImagePainter(model = list.get(index).image),
                            contentDescription = list.get(index).name,
                            modifier = Modifier
                                .height(120.dp)
                                .width(120.dp)


                        )

                        Text(
                            text = "$"+list.get(index).price,
                            color = Color.Black,
                            fontSize = 14.sp,
//                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                        )
                        Text(
                            text = list.get(index).name,
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                        )
                    }
                }

            }
        }
    }




}



@Composable
fun AppBarIcon(icon: Any, function: () -> Unit) {

}

fun getData(): MutableList<Mobile> {
    var list = mutableListOf<Mobile>()
    list.add(
        Mobile(
            101,
            "F21 Gallxay",
            "Samsung",
            9000,
            "https://m.media-amazon.com/images/I/41ZGJxnJu1S.jpg"
        )
    )
    list.add(
        Mobile(
            102,
            "V1 Pro",
            "Vivo",
            8000,
            "https://m.media-amazon.com/images/I/51GkJLzb9NL._SY741_.jpg"
        )
    )
    list.add(
        Mobile(
            103,
            "R6 Pro ",
            "Realme",
            7000,
            "https://images-eu.ssl-images-amazon.com/images/I/4147W6koDNL._SX300_SY300_QL70_FMwebp_.jpg"
        )
    )
    list.add(
        Mobile(
            104,
            "Oppo Proud",
            "Oppo",
            6000,
            "https://images-eu.ssl-images-amazon.com/images/I/41BnHjRP0ZS._SX300_SY300_QL70_FMwebp_.jpg"
        )
    )
    list.add(
        Mobile(
            105,
            "M1 Pro",
            "Moto",
            5000,
            "https://m.media-amazon.com/images/I/31GOIteqGpL._AC_UY218_.jpg"
        )
    )
    list.add(
        Mobile(
            106,
            "Note5",
            "Redmi",
            4000,
            "https://m.media-amazon.com/images/I/41P4Al+S3zL._SY300_SX300_.jpg"
        )
    )
    list.add(
        Mobile(
            107,
            "In 20",
            "Infix",
            3000,
            "https://m.media-amazon.com/images/I/41P4Al+S3zL._SY300_SX300_.jpg"
        )
    )
    list.add(
        Mobile(
            108,
            "N7",
            "Nokia",
            2000,
            "https://m.media-amazon.com/images/I/41P4Al+S3zL._SY300_SX300_.jpg"
        )
    )
    list.add(
        Mobile(
            109,
            "G1 Pro",
            "Ola",
            1000,
            "https://m.media-amazon.com/images/I/41P4Al+S3zL._SY300_SX300_.jpg"
        )
    )

    return list;

}