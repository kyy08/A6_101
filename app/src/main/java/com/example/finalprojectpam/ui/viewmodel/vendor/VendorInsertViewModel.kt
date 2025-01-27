package com.example.finalprojectpam.ui.viewmodel.vendor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Vendor
import com.example.finalprojectpam.repository.VendorRepository
import kotlinx.coroutines.launch

class VendorInsertViewModel(private val vndr: VendorRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set
    fun updateInsertVndrState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertVndr() {
        viewModelScope.launch {
            try {
                vndr.insertVendor(uiState.insertUiEvent.toVndr())
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
    val id_vendor: String = "",
    val nama_vendor: String = "",
    val jenis_vendor: String = "",
    val kontak_vendor: String = ""

)

fun InsertUiEvent.toVndr(): Vendor = Vendor(
    id_vendor = id_vendor,
    nama_vendor= nama_vendor,
    jenis_vendor = jenis_vendor,
    kontak_vendor= kontak_vendor
)

fun Vendor.toUiStateVndr(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Vendor.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_vendor = id_vendor,
    nama_vendor= nama_vendor,
    jenis_vendor = jenis_vendor,
    kontak_vendor= kontak_vendor
)