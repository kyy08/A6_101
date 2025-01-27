package com.example.finalprojectpam.ui.viewmodel.lokasi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Lokasi
import com.example.finalprojectpam.repository.LokasiRepository
import kotlinx.coroutines.launch

class LokasiUpdateViewModel(private val lks: LokasiRepository) : ViewModel() {
    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun loadLokasi(id_lokasi: String) {
        viewModelScope.launch {
            try {
                val lokasi = lks.getLokasiById(id_lokasi)
                uiState = UpdateUiState(updateUiEvent = lokasi.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateLokasiState(updateUiEvent: UpdateUiEvent) {
        uiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    suspend fun updateLokasi(id_lokasi: String) {
        viewModelScope.launch {
            try {
                lks.updateLokasi(id_lokasi, uiState.updateUiEvent.toLokasi())
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
    val id_lokasi: String = "",
    val nama_lokasi: String = "",
    val alamat_lokasi: String = "",
    val kapasitas: String = ""

)

fun UpdateUiEvent.toLokasi(): Lokasi = Lokasi(
    id_lokasi = id_lokasi,
    nama_lokasi = nama_lokasi,
    alamat_lokasi = alamat_lokasi,
    kapasitas = kapasitas
)

fun Lokasi.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    id_lokasi = id_lokasi,
    nama_lokasi = nama_lokasi,
    alamat_lokasi = alamat_lokasi,
    kapasitas = kapasitas
)