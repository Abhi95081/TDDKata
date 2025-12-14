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
import androidx.compose.ui.Modifier
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
