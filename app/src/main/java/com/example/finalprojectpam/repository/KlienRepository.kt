package com.example.finalprojectpam.repository

import com.example.finalprojectpam.model.Klien
import com.example.finalprojectpam.service.KlienService
import java.io.IOException

interface KlienRepository{
    suspend fun getKlien(): List<Klien>
    suspend fun insertKlien(mahasiswa: Klien)
    suspend fun updateKlien(id: String, klien: Klien)
    suspend fun deleteKlien(id: String)
    suspend fun getKlienById(id: String): Klien
}

class NetworkKlienRepository(
    private val klienApiService: KlienService
) : KlienRepository{
    override suspend fun insertKlien(klien: Klien) {
        klienApiService.insertKlien(klien)
    }

    override suspend fun updateKlien(id: String, klien: Klien) {
        klienApiService.updateKlien(id, klien)
    }

    override suspend fun deleteKlien(id: String) {
        try {
            val response = klienApiService.deleteKlien(id)
            if (!response.isSuccessful){
                throw IOException("Failed to delete klien. HTTP Status code: ${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getKlien(): List<Klien> = klienApiService.getKlien()
    override suspend fun getKlienById(id: String): Klien {
        try {
            return klienApiService.getKlienById(id)
        } catch (e: IOException) {
            throw IOException("Failed to fetch klien with Id: $id. Network error occurred.", e)
        }
    }
}