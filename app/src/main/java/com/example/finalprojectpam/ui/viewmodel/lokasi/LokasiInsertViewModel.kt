package com.example.finalprojectpam.ui.viewmodel.lokasi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Lokasi
import com.example.finalprojectpam.repository.LokasiRepository
import kotlinx.coroutines.launch

class LokasiInsertViewModel(private val lks: LokasiRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set
    fun updateInsertLksState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertLks() {
        viewModelScope.launch {
            try {
                lks.insertLokasi(uiState.insertUiEvent.toLks())
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
    val id_lokasi: String = "",
    val nama_lokasi: String = "",
    val alamat_lokasi: String = "",
    val kapasitas: String = ""

)

fun InsertUiEvent.toLks(): Lokasi = Lokasi(
    id_lokasi = id_lokasi,
    nama_lokasi = nama_lokasi,
    alamat_lokasi = alamat_lokasi,
    kapasitas = kapasitas
)

fun Lokasi.toUiStateLks(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Lokasi.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_lokasi = id_lokasi,
    nama_lokasi = nama_lokasi,
    alamat_lokasi = alamat_lokasi,
    kapasitas = kapasitas
)