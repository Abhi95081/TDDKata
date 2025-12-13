package com.example.tddkata.api

import com.example.tddkata.Datamodel.Sweet
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// -------------------- API --------------------
interface SweetApi {

    @GET("/api/sweets")
    suspend fun getSweets(): List<Sweet>

    @POST("/api/sweets/{id}/purchase")
    suspend fun purchaseSweet(@Path("id") id: Int)
}