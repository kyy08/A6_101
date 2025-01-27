package com.example.finalprojectpam.repository

import com.example.finalprojectpam.model.Klien
import com.example.finalprojectpam.service.KlienService

interface KlienRepository {
    suspend fun getKlien(): List<Klien>
    suspend fun insertKlien(klien: Klien)
    suspend fun updateKlien(id_klien: String, klien: Klien)
    suspend fun deleteKlien(id_klien: String)
    suspend fun getKlienById(id_klien: String): Klien
}

class NetworkKlienRepository(private val klienService: KlienService)
    : KlienRepository
{
    override suspend fun getKlien(): List<Klien> = klienService.getKlien()

    override suspend fun insertKlien(klien: Klien) {
        klienService.insertKlien(klien)
    }

    override suspend fun updateKlien(id_klien: String, klien: Klien) {
        klienService.updateKlien(id_klien, klien)
    }

    override suspend fun deleteKlien(id_klien: String) {
        try {
            val response = klienService.deleteKlien(id_klien)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete klien. HTTP Status Code: ${response.code()}")
            }
            else{
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getKlienById(id_klien: String): Klien {
        return klienService.getKlienById(id_klien)
    }
}