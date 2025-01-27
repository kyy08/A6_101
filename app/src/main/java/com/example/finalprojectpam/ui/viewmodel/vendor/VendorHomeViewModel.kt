package com.example.finalprojectpam.ui.viewmodel.vendor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam.model.Vendor
import com.example.finalprojectpam.repository.VendorRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState {
    data class Success(val vendor: List<Vendor>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}


class VendorHomeViewModel(private val vndr: VendorRepository) : ViewModel() {

    var vndrUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getVndr()
    }

    fun getVndr() {
        viewModelScope.launch {
            vndrUIState = HomeUiState.Loading
            vndrUIState= try {
                HomeUiState.Success(vndr.getVendor())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deleteVndr(id_vendor: String) {
        viewModelScope.launch {
            try {
                vndr.deleteVendor(id_vendor)
            } catch (e: IOException) {
                vndrUIState = HomeUiState.Error
            } catch (e: HttpException) {
                vndrUIState = HomeUiState.Error
            }
        }
    }
}