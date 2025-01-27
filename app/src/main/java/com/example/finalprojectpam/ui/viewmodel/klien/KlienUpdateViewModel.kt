package com.example.finalprojectpam.ui.viewmodel.klien

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Klien
import com.example.finalprojectpam.repository.KlienRepository
import kotlinx.coroutines.launch

class KlienUpdateViewModel(private val kln: KlienRepository) : ViewModel() {
    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun loadKlien(id_klien: String) {
        viewModelScope.launch {
            try {
                val klien = kln.getKlienById(id_klien)
                uiState = UpdateUiState(updateUiEvent = klien.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateKlienState(updateUiEvent: UpdateUiEvent) {
        uiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    suspend fun updateKlien(id_klien: String) {
        viewModelScope.launch {
            try {
                kln.updateKlien(id_klien,uiState.updateUiEvent.toKlien())
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
    val id_klien: String = "",
    val nama_klien: String = "",
    val kontak_klien: String = ""

)

fun UpdateUiEvent.toKlien(): Klien = Klien(
    id_klien = id_klien,
    nama_klien = nama_klien,
    kontak_klien = kontak_klien
)

fun Klien.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    id_klien = id_klien,
    nama_klien = nama_klien,
    kontak_klien = kontak_klien
)