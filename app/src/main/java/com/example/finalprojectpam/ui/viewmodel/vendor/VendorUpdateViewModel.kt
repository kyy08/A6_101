package com.example.finalprojectpam.ui.viewmodel.vendor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Lokasi
import com.example.finalprojectpam.model.Vendor
import com.example.finalprojectpam.repository.LokasiRepository
import com.example.finalprojectpam.repository.VendorRepository
import kotlinx.coroutines.launch

class VendorUpdateViewModel(private val vndr: VendorRepository) : ViewModel() {
    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun loadVendor(id_vendor: String) {
        viewModelScope.launch {
            try {
                val lokasi = vndr.getVendorById(id_vendor)
                uiState = UpdateUiState(updateUiEvent = lokasi.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateVendorState(updateUiEvent: UpdateUiEvent) {
        uiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    suspend fun updateVendor(id_vendor: String) {
        viewModelScope.launch {
            try {
                vndr.updateVendor(id_vendor, uiState.updateUiEvent.toVendor())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class UpdateUiState(
    val updateUiEvent: UpdateUiEvent = UpdateUiEvent()
)

data class UpdateUiEvent(
    val id_vendor: String = "",
    val nama_vendor: String = "",
    val jenis_vendor: String = "",
    val kontak_vendor: String = ""

)

fun UpdateUiEvent.toVendor(): Vendor = Vendor(
    id_vendor = id_vendor,
    nama_vendor= nama_vendor,
    jenis_vendor = jenis_vendor,
    kontak_vendor= kontak_vendor
)

fun Vendor.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    id_vendor = id_vendor,
    nama_vendor= nama_vendor,
    jenis_vendor = jenis_vendor,
    kontak_vendor= kontak_vendor
)