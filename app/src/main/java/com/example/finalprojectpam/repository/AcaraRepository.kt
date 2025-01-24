package com.example.finalprojectpam.repository

import com.example.finalprojectpam.model.Acara
import com.example.finalprojectpam.service.AcaraService
import java.io.IOException

interface AcaraRepository{
    suspend fun getAcara(): List<Acara>
    suspend fun insertAcara(acara: Acara)
    suspend fun updateAcara(id: String, acara: Acara)
    suspend fun deleteAcara(id: String)
    suspend fun getAcaraById(id: String): Acara
}

class NetworkAcaraRepository(
    private val acaraApiService: AcaraService
) : AcaraRepository{
    override suspend fun insertAcara(acara: Acara) {
        acaraApiService.insertAcara(acara)
    }

    override suspend fun updateAcara(id: String, acara: Acara) {
        acaraApiService.updateAcara(id,acara)
    }

    override suspend fun deleteAcara(id: String) {
        try {
            val response = acaraApiService.deleteAcara(id)
            if (!response.isSuccessful){
                throw IOException("Failed to delete acara. HTTP Status code: ${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getAcara(): List<Acara> = acaraApiService.getAcara()
    override suspend fun getAcaraById(id: String): Acara {
        try {
            return acaraApiService.getAcaraById(id)
        } catch (e: IOException) {
            throw IOException("Failed to fetch acara with Id: $id. Network error occurred.", e)
        }
    }

}