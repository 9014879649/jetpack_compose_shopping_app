package com.nag.myamazon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nag.myamazon.navigation.Navigator
import com.nag.myamazon.ui.theme.MyAmazonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme  {
                Navigator()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Navigator()
    }
}

