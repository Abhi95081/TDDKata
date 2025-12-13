package com.example.tddkata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tddkata.Datamodel.Sweet
import com.example.tddkata.screens.SweetListContent
import com.example.tddkata.screens.SweetListScreen
import com.example.tddkata.ui.theme.TDDKataTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TDDKataTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("🍬 Sweet Shop") })
                    }
                ) { padding ->
                    SweetListScreen(
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewSweetShop() {
    TDDKataTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("🍬 Sweet Shop") })
            }
        ) { padding ->
            SweetListContent(
                modifier = Modifier.padding(padding),
                sweets = listOf(
                    Sweet(
                        id = 1,
                        name = "Samosa",
                        category = "Snack",
                        price = 10.0,
                        quantity = 5,
                        image_url = null
                    ),
                    Sweet(
                        id = 2,
                        name = "Gulab Jamun",
                        category = "Dessert",
                        price = 15.0,
                        quantity = 0,
                        image_url = null
                    ),
                    Sweet(
                        id = 3,
                        name = "Ladoo",
                        category = "Dessert",
                        price = 8.0,
                        quantity = 10,
                        image_url = null
                    )
                ),
                onPurchase = {}
            )
        }
    }
}
