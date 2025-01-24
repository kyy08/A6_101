package com.example.finalprojectpam.repository

import com.example.finalprojectpam.model.Vendor
import com.example.finalprojectpam.service.VendorService
import java.io.IOException

interface VendorRepository {
    suspend fun getVendor(): List<Vendor>
    suspend fun insertVendor(vendor: Vendor)
    suspend fun updateVendor(id: String, vendor: Vendor)
    suspend fun deleteVendor(id: String)
    suspend fun getVendorById(id: String): Vendor
}

class NetworkVendorRepository(
    private val vendorApiService: VendorService
) : VendorRepository {
    override suspend fun insertVendor(vendor: Vendor) {
        vendorApiService.insertVendor(vendor)
    }

    override suspend fun updateVendor(id: String, vendor: Vendor) {
        vendorApiService.updateVendor(id, vendor)
    }

    override suspend fun deleteVendor(id: String) {
        try {
            val response = vendorApiService.deleteVendor(id)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete vendor. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getVendor(): List<Vendor> = vendorApiService.getVendor()

    override suspend fun getVendorById(id: String): Vendor {
        try {
            return vendorApiService.getVendorById(id)
        } catch (e: IOException) {
            throw IOException("Failed to fetch vendor with Id: $id. Network error occurred.", e)
        }
    }
}
