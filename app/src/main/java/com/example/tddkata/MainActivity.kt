package com.example.tddkata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tddkata.Datamodel.Sweet
import com.example.tddkata.screens.SweetListContent
import com.example.tddkata.screens.SweetListScreen
import com.example.tddkata.ui.theme.TDDKataTheme
import com.example.tddkata.viewmodel.SweetViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


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
