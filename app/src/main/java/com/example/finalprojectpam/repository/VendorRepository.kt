package com.example.finalprojectpam.repository

import com.example.finalprojectpam.model.Vendor
import com.example.finalprojectpam.service.VendorService

interface VendorRepository {
    suspend fun getVendor(): List<Vendor>
    suspend fun insertVendor(vendor: Vendor)
    suspend fun updateVendor(id_vendor: String, vendor: Vendor)
    suspend fun deleteVendor(id_vendor: String)
    suspend fun getVendorById(id_vendor: String): Vendor
}

class NetworkVendorRepository(private val vendorService: VendorService)
    : VendorRepository
{
    override suspend fun getVendor(): List<Vendor> = vendorService.getVendor()

    override suspend fun insertVendor(vendor: Vendor) {
        vendorService.insertVendor(vendor)
    }

    override suspend fun updateVendor(id_vendor: String, vendor: Vendor) {
        vendorService.updateVendor(id_vendor, vendor)
    }

    override suspend fun deleteVendor(id_vendor: String) {
        try {
            val response = vendorService.deleteVendor(id_vendor)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete vendor. HTTP Status Code: ${response.code()}")
            }
            else{
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getVendorById(id_vendor: String): Vendor {
        return vendorService.getVendorById(id_vendor)
    }
}