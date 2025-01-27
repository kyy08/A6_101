package com.example.finalprojectpam.ui.viewmodel.acara

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Acara
import com.example.finalprojectpam.repository.AcaraRepository
import kotlinx.coroutines.launch

class AcaraInsertViewModel(private val acr: AcaraRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set
    fun updateInsertAcrState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertAcr() {
        viewModelScope.launch {
            try {
                acr.insertAcara(uiState.insertUiEvent.toAcr())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val id_acara: String = "",
    val nama_acara: String = "",
    val deskripsi_acara: String = "",
    val tanggal_mulai: String = "",
    val tanggal_berakhir: String = "",
    val id_lokasi: String = "",
    val id_klien: String = "",
    val status_acara: String = ""


)

fun InsertUiEvent.toAcr(): Acara = Acara(
    id_acara = id_acara,
    nama_acara = nama_acara,
    deskripsi_acara = deskripsi_acara,
    tanggal_mulai = tanggal_mulai,
    tanggal_berakhir = tanggal_berakhir,
    id_lokasi = id_lokasi,
    id_klien = id_klien,
    status_acara = status_acara
)

fun Acara.toUiStateAcr(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Acara.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_acara = id_acara,
    nama_acara = nama_acara,
    deskripsi_acara = deskripsi_acara,
    tanggal_mulai = tanggal_mulai,
    tanggal_berakhir = tanggal_berakhir,
    id_lokasi = id_lokasi,
    id_klien = id_klien,
    status_acara = status_acara
)