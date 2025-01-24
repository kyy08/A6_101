package com.example.finalprojectpam.service

import com.example.finalprojectpam.model.Lokasi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface LokasiService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("read.php")
    suspend fun getLokasi(): List<Lokasi>

    @GET("read1.php/{id}")
    suspend fun getLokasiById(@Query("id") id: String): Lokasi

    @POST("create.php")
    suspend fun insertLokasi(@Body lokasi: Lokasi)

    @PUT("update.php/{id}")
    suspend fun updateLokasi(@Query("id") id: String, @Body lokasi: Lokasi)

    @DELETE("delete.php/{id}")
    suspend fun deleteLokasi(@Query("id") id: String): Response<Void>
}
