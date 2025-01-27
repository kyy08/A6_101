package com.example.finalprojectpam.ui.viewmodel.klien

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Klien
import com.example.finalprojectpam.repository.KlienRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState {
    data class Success(val klien: List<Klien>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}


class KlienHomeViewModel(private val kln: KlienRepository) : ViewModel() {

    var klnUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getKln()
    }

    fun getKln() {
        viewModelScope.launch {
            klnUIState = HomeUiState.Loading
            klnUIState = try {
                HomeUiState.Success(kln.getKlien())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deleteKln(id_klien: String) {
        viewModelScope.launch {
            try {
                kln.deleteKlien(id_klien)
            } catch (e: IOException) {
                klnUIState = HomeUiState.Error
            } catch (e: HttpException) {
                klnUIState = HomeUiState.Error
            }
        }
    }
}