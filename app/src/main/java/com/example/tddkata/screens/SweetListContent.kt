package com.example.tddkata.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tddkata.Datamodel.Sweet
import com.example.tddkata.SweetCard


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
