package com.example.finalprojectpam.ui.viewmodel.acara

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Acara
import com.example.finalprojectpam.repository.AcaraRepository
import kotlinx.coroutines.launch

class AcaraUpdateViewModel(private val acr: AcaraRepository) : ViewModel() {
    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun loadAcara(id_acara: String) {
        viewModelScope.launch {
            try {
                val acara= acr.getAcaraById(id_acara)
                uiState = UpdateUiState(updateUiEvent = acara.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateAcaraState(updateUiEvent: UpdateUiEvent) {
        uiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    suspend fun updateAcara(id_acara: String) {
        viewModelScope.launch {
            try {
                acr.updateAcara(id_acara,uiState.updateUiEvent.toAcara())
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
    val id_acara: String = "",
    val nama_acara: String = "",
    val deskripsi_acara: String = "",
    val tanggal_mulai: String = "",
    val tanggal_berakhir: String = "",
    val id_lokasi: String = "",
    val id_klien: String = "",
    val status_acara: String = "",

)

fun UpdateUiEvent.toAcara(): Acara = Acara(
    id_acara = id_acara,
    nama_acara = nama_acara,
    deskripsi_acara = deskripsi_acara,
    tanggal_mulai = tanggal_mulai,
    tanggal_berakhir = tanggal_berakhir,
    id_lokasi = id_lokasi,
    id_klien = id_klien,
    status_acara = status_acara
)

fun Acara.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    id_acara = id_acara,
    nama_acara = nama_acara,
    deskripsi_acara = deskripsi_acara,
    tanggal_mulai = tanggal_mulai,
    tanggal_berakhir = tanggal_berakhir,
    id_lokasi = id_lokasi,
    id_klien = id_klien,
    status_acara = status_acara
)