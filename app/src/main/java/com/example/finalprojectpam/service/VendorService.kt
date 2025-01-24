package com.example.finalprojectpam.service

import com.example.finalprojectpam.model.Vendor
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface VendorService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("read.php")
    suspend fun getVendor(): List<Vendor>

    @GET("read1.php/{id}")
    suspend fun getVendorById(@Query("id") id: String): Vendor

    @POST("create.php")
    suspend fun insertVendor(@Body vendor: Vendor)

    @PUT("update.php/{id}")
    suspend fun updateVendor(@Query("id") id: String, @Body vendor: Vendor)

    @DELETE("delete.php/{id}")
    suspend fun deleteVendor(@Query("id") id: String): Response<Void>
}
