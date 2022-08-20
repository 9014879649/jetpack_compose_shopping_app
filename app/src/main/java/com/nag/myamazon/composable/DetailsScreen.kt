@file:OptIn(ExperimentalMaterialApi::class)

package com.nag.myamazon.composable

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
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

    var TAG ="DetailsScreen"
    var item:Mobile? = null
    val mContext = LocalContext.current





    Log.e(TAG,"Before Cart Size====="+sharedViewModel.getListOfItems().size)

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(

            title = { Text(text = ""+sharedViewModel.getCartItem()?.brand) },
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back",Modifier.padding(15.dp))
                }

            }

        )



        Column( modifier = Modifier.weight(9f),horizontalAlignment = Alignment.CenterHorizontally) {
            Card(onClick = {  }) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = rememberAsyncImagePainter(model = sharedViewModel.getCartItem()?.image),
                        contentDescription = sharedViewModel.getCartItem()?.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(15.dp),
                    )



                    Spacer(modifier = Modifier.size(15.dp))
                    Text(text = "$ "+sharedViewModel.getCartItem()?.price,  color = Color.Red, fontSize = 20.sp)
                    Text(text = ""+sharedViewModel.getCartItem()?.brand+" - "+sharedViewModel.getCartItem()?.name, color = Color.Black, fontSize = 18.sp)

                    Spacer(modifier = Modifier.size(15.dp))
                    Text( fontSize = 16.sp, color = Color.Gray, text = "Description : * Estimated against the usage profile of an average/typical user. Independently assessed by Strategy Analytics between 2021.12.08–12.20 in USA and UK with pre-release versions of SM-S901, SM-S906, SM-S908 under default setting using 5G Sub6 networks (NOT tested under 5G mmWave network). Actual battery life varies by network environment, features and apps used, frequency of calls and messages, number of times charged, and many other factors.\n" +
                            "\n" +
                            "Images shown here are for representational purpose only, actual may vary. All features, specifications and prices are subject to change without prior notice. Model availability may vary from location to location.\n" +
                            "\n" +
                            "User Available Memory : User memory is less than the total memory due to storage of the operating system and software used to operate the device features. Actual user memory will vary depending on the operator and may change after software upgrades are performed\n" +
                            "\n" +
                            "Network : The bandwidths supported by the device may vary depending on the region or service provider\n" +
                            "\n" +
                            "All specifications and descriptions provided herein may be different from the actual specifications and descriptions for the product. Samsung reserves the right to make changes to this web page and the product described herein, at anytime, without obligation on Samsung to provide notification of such change. All functionality, features, specifications, GUI and other product information provided in this web page including, but not limited to, the benefits, design, pricing, components, performance, availability, and capabilities of the product are subject to change without notice or obligation. The contents within the screen are simulated images and are for demonstration purposes only.\n" +
                            "\n" +
                            "Creative visualization. Images shown here are for representational purpose only, actual may vary.\n" +
                            "\n" +
                            "*S Pen Pro and S Pen Fold Edition sold separately. Only use the Samsung S Pen Pro or the S Pen Fold Edition designed exclusively for Galaxy Z Fold3 5G. All other S Pens or stylus pens not designed for Galaxy Z Fold3 5G (including those by other manufacturers) may damage the screen.\n" +
                            "\n" +
                            "*Galaxy Z Fold3 5G and Z Filp3 5G is rated as IPX8. IPX8 is based on test conditions for submersion in up to 1.5 meters of freshwater for up to 30 minutes. Not advised for beach or pool use. Not dust resistant.\n" +
                            "\n" +
                            "Images shown here are for representational purpose only, actual may vary. All features, specifications and prices are subject to change without prior notice. Model availability may vary from location to location.\n" +
                            "\n" +
                            "*Measured diagonally, the screen size is 16.21cm (6.4\") in the full rectangle and 15.87cm (6.2\") with accounting for the rounded corners.\n" +
                            "\n" +
                            "6000 mAh (typical)*\n" +
                            "\n" +
                            "*Typical value tested under third-party laboratory condition. Typical value is the estimated average value considering the deviation in battery capacity among the battery samples tested under IEC 61960 standard. Rated (minimum) capacity is 5,830 mAh. Actual battery life may vary depending on network environment, usage patterns and other factors.")

                }



            }
        }

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
                    Log.e(TAG,"After Item Added Cart Size====="+sharedViewModel.getListOfItems().size)
                    Toast.makeText(mContext,"Item Added!",Toast.LENGTH_LONG).show()

                },modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
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
}