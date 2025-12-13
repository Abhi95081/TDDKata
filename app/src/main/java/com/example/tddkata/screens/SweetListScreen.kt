package com.example.tddkata.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tddkata.SweetListContent
import com.example.tddkata.viewmodel.SweetViewModel


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
