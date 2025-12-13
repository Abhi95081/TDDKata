package com.example.tddkata.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tddkata.Datamodel.Sweet
import com.example.tddkata.viewmodel.getSweetImageRes

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