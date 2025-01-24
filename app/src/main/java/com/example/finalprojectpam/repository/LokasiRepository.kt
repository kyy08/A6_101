package com.example.finalprojectpam.repository

import com.example.finalprojectpam.model.Lokasi
import com.example.finalprojectpam.service.LokasiService
import java.io.IOException

interface LokasiRepository {
    suspend fun getLokasi(): List<Lokasi>
    suspend fun insertLokasi(lokasi: Lokasi)
    suspend fun updateLokasi(id: String, lokasi: Lokasi)
    suspend fun deleteLokasi(id: String)
    suspend fun getLokasiById(id: String): Lokasi
}

class NetworkLokasiRepository(
    private val lokasiApiService: LokasiService
) : LokasiRepository {
    override suspend fun insertLokasi(lokasi: Lokasi) {
        lokasiApiService.insertLokasi(lokasi)
    }

    override suspend fun updateLokasi(id: String, lokasi: Lokasi) {
        lokasiApiService.updateLokasi(id, lokasi)
    }

    override suspend fun deleteLokasi(id: String) {
        try {
            val response = lokasiApiService.deleteLokasi(id)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete lokasi. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getLokasi(): List<Lokasi> = lokasiApiService.getLokasi()
    override suspend fun getLokasiById(id: String): Lokasi {
        try {
            return lokasiApiService.getLokasiById(id)
        } catch (e: IOException) {
            throw IOException("Failed to fetch lokasi with Id: $id. Network error occurred.", e)
        }
    }
}
