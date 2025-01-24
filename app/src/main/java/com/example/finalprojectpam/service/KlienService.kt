package com.example.finalprojectpam.service

import com.example.finalprojectpam.model.Acara
import com.example.finalprojectpam.model.Klien
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface KlienService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("read.php")
    suspend fun getKlien(): List<Klien>

    @GET("read1.php/{id}")
    suspend fun getKlienById(@Query("id") id: String): Klien

    @POST("create.php")
    suspend fun insertKlien(@Body klien: Klien)

    @PUT("update.php/{id}")
    suspend fun updateKlien(@Query("id")id: String, @Body klien: Klien)

    @DELETE("delete.php/{id}")
    suspend fun deleteKlien(@Query("id")id: String): Response<Void>
}