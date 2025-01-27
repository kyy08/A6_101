package com.example.finalprojectpam.ui.viewmodel.vendor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Vendor
import com.example.finalprojectpam.repository.VendorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val vendor: Vendor) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}

class VendorDetailViewModel(private val vndr: VendorRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getVendorById(id_vendor: String) {
        viewModelScope.launch {
            try {
                val lokasi = vndr.getVendorById(id_vendor)
                _uiState.value = DetailUiState.Success(lokasi)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.localizedMessage ?: "Terjadi kesalahan.")
            }
        }
    }


    fun deleteVndr(id_vendor: String) {
        viewModelScope.launch {
            try {
                vndr.deleteVendor(id_vendor)
                _uiState.value = DetailUiState.Error("Data Lokasi telah dihapus.")
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.localizedMessage ?: "Gagal menghapus data.")
            }
        }
    }
}
