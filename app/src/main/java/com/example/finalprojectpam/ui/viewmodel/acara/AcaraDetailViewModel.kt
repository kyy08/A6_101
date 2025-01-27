package com.example.finalprojectpam.ui.viewmodel.acara

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Acara
import com.example.finalprojectpam.repository.AcaraRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val acara: Acara) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}

class AcaraDetailViewModel(private val acr: AcaraRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getAcaraById(id_acara: String) {
        viewModelScope.launch {
            try {
                val acara = acr.getAcaraById(id_acara)
                _uiState.value = DetailUiState.Success(acara)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.localizedMessage ?: "Terjadi kesalahan.")
            }
        }
    }


    fun deleteAcr(id_acara: String) {
        viewModelScope.launch {
            try {
                acr.deleteAcara(id_acara)
                _uiState.value = DetailUiState.Error("Data Acara telah dihapus.")
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.localizedMessage ?: "Gagal menghapus data.")
            }
        }
    }
}
