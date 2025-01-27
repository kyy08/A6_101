package com.example.finalprojectpam.ui.viewmodel.acara

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Acara
import com.example.finalprojectpam.repository.AcaraRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState {
    data class Success(val acara: List<Acara>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}


class AcaraHomeViewModel(private val acr: AcaraRepository) : ViewModel() {

    var acrUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getAcr()
    }

    fun getAcr() {
        viewModelScope.launch {
            acrUIState = HomeUiState.Loading
            acrUIState = try {
                HomeUiState.Success(acr.getAcara())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deleteAcr(id_acara: String) {
        viewModelScope.launch {
            try {
                acr.deleteAcara(id_acara)
            } catch (e: IOException) {
                acrUIState = HomeUiState.Error
            } catch (e: HttpException) {
                acrUIState = HomeUiState.Error
            }
        }
    }
}