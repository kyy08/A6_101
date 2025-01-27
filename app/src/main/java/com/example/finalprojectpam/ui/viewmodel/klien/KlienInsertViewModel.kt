package com.example.finalprojectpam.ui.viewmodel.klien

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Klien
import com.example.finalprojectpam.repository.KlienRepository
import kotlinx.coroutines.launch

class KlienInsertViewModel(private val kln: KlienRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set
    fun updateInsertKlnState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertKln() {
        viewModelScope.launch {
            try {
                kln.insertKlien(uiState.insertUiEvent.toKln())
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
    val id_klien: String = "",
    val nama_klien: String = "",
    val kontak_klien: String = ""

)

fun InsertUiEvent.toKln(): Klien = Klien(
    id_klien = id_klien,
    nama_klien = nama_klien,
    kontak_klien = kontak_klien
)

fun Klien.toUiStateKln(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Klien.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_klien = id_klien,
    nama_klien = nama_klien,
    kontak_klien = kontak_klien
)