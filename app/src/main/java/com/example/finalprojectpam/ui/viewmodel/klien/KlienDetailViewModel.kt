package com.example.finalprojectpam.ui.viewmodel.klien

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Klien
import com.example.finalprojectpam.repository.KlienRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val klien: Klien) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}

class KlienDetailViewModel(private val kln: KlienRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getKlienById(id_klien: String) {
        viewModelScope.launch {
            try {
                val klien = kln.getKlienById(id_klien)
                _uiState.value = DetailUiState.Success(klien)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.localizedMessage ?: "Terjadi kesalahan.")
            }
        }
    }


    fun deleteKln(id_klien: String) {
        viewModelScope.launch {
            try {
                kln.deleteKlien(id_klien)
                _uiState.value = DetailUiState.Error("Data Lokasi telah dihapus.")
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.localizedMessage ?: "Gagal menghapus data.")
            }
        }
    }
}
