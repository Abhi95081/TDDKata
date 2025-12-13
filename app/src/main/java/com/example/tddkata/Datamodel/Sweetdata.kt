package com.example.tddkata.Datamodel

// -------------------- DATA MODEL --------------------
data class Sweet(
    val id: Int,
    val name: String,
    val category: String?,
    val price: Double,
    val quantity: Int,
    val image_url: String?
)