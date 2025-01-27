package com.example.finalprojectpam.ui.viewmodel.lokasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Lokasi
import com.example.finalprojectpam.repository.LokasiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val lokasi: Lokasi) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}

class LokasiDetailViewModel(private val lks: LokasiRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getLokasiById(id_lokasi: String) {
        viewModelScope.launch {
            try {
                val lokasi = lks.getLokasiById(id_lokasi)
                _uiState.value = DetailUiState.Success(lokasi)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.localizedMessage ?: "Terjadi kesalahan.")
            }
        }
    }


    fun deleteLks(id_lokasi: String) {
        viewModelScope.launch {
            try {
                lks.deleteLokasi(id_lokasi)
                _uiState.value = DetailUiState.Error("Data Lokasi telah dihapus.")
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.localizedMessage ?: "Gagal menghapus data.")
            }
        }
    }
}
