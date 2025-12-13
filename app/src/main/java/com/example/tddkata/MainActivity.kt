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
import com.example.tddkata.ui.theme.TDDKataTheme
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

// -------------------- DATA MODEL --------------------
data class Sweet(
    val id: Int,
    val name: String,
    val category: String?,
    val price: Double,
    val quantity: Int,
    val image_url: String?
)

// -------------------- API --------------------
interface SweetApi {

    @GET("/api/sweets")
    suspend fun getSweets(): List<Sweet>

    @POST("/api/sweets/{id}/purchase")
    suspend fun purchaseSweet(@Path("id") id: Int)
}

// -------------------- RETROFIT --------------------
object ApiClient {

    private const val BASE_URL = "http://10.0.2.2:8000/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    val api: SweetApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SweetApi::class.java)
}

// -------------------- VIEWMODEL --------------------
class SweetViewModel : ViewModel() {

    private val _sweets = MutableStateFlow<List<Sweet>>(emptyList())
    val sweets: StateFlow<List<Sweet>> = _sweets

    init {
        loadSweets()
    }

    private fun loadSweets() {
        viewModelScope.launch {
            try {
                _sweets.value = ApiClient.api.getSweets()
            } catch (e: Exception) {
                e.printStackTrace()
                _sweets.value = emptyList()
            }
        }
    }

    fun purchaseSweet(id: Int) {
        viewModelScope.launch {
            try {
                ApiClient.api.purchaseSweet(id)
                loadSweets()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

// -------------------- IMAGE MAPPER --------------------
fun getSweetImageRes(name: String): Int {
    return when (name.lowercase()) {
        "samosa" -> R.drawable.samosa
        "gulab jamun" -> R.drawable.gulabjamun
        "ladoo" -> R.drawable.ladoo
        else -> R.drawable.samosa
    }
}

// -------------------- ACTIVITY --------------------
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

// -------------------- SCREEN (WITH VIEWMODEL) --------------------
@Composable
fun SweetListScreen(
    modifier: Modifier = Modifier,
    viewModel: SweetViewModel = viewModel()
) {
    val sweets by viewModel.sweets.collectAsState()

    SweetListContent(
        modifier = modifier,
        sweets = sweets,
        onPurchase = { viewModel.purchaseSweet(it) }
    )
}

// -------------------- PURE UI (PREVIEW SAFE) --------------------
@Composable
fun SweetListContent(
    modifier: Modifier = Modifier,
    sweets: List<Sweet>,
    onPurchase: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(sweets) { sweet ->
            SweetCard(
                sweet = sweet,
                onPurchase = { onPurchase(sweet.id) }
            )
        }
    }
}

@Composable
fun SweetCard(
    sweet: Sweet,
    onPurchase: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {

            Image(
                painter = painterResource(
                    id = getSweetImageRes(sweet.name)
                ),
                contentDescription = sweet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = sweet.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text("Price: ₹${sweet.price}")
                Text("Stock: ${sweet.quantity}")

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = onPurchase,
                    enabled = sweet.quantity > 0,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (sweet.quantity > 0) "Purchase" else "Out of Stock"
                    )
                }
            }
        }
    }
}

// -------------------- PREVIEW --------------------
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
