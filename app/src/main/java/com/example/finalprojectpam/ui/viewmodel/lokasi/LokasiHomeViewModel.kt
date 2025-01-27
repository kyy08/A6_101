package com.example.finalprojectpam.ui.viewmodel.lokasi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Lokasi
import com.example.finalprojectpam.repository.LokasiRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState {
    data class Success(val lokasi: List<Lokasi>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}


class LokasiHomeViewModel(private val lks: LokasiRepository) : ViewModel() {

    var lksUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getLks()
    }

    fun getLks() {
        viewModelScope.launch {
            lksUIState = HomeUiState.Loading
            lksUIState = try {
                HomeUiState.Success(lks.getLokasi())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deleteLks(id_lokasi: String) {
        viewModelScope.launch {
            try {
                lks.deleteLokasi(id_lokasi)
            } catch (e: IOException) {
                lksUIState = HomeUiState.Error
            } catch (e: HttpException) {
                lksUIState = HomeUiState.Error
            }
        }
    }
}