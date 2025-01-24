package com.example.finalprojectpam.service

import com.example.finalprojectpam.model.Acara
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AcaraService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("read.php")
    suspend fun getAcara(): List<Acara>

    @GET("read1.php/{id}")
    suspend fun getAcaraById(@Query("id") id: String): Acara

    @POST("create.php")
    suspend fun insertAcara(@Body acara: Acara)

    @PUT("update.php/{id}")
    suspend fun updateAcara(@Query("id")id: String, @Body acara: Acara)

    @DELETE("delete.php/{id}")
    suspend fun deleteAcara(@Query("nim")nim: String): Response<Void>

}