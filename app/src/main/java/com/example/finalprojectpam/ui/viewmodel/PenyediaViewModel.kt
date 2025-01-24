package com.example.finalprojectpam.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalprojectpam.AcaraApplications
import com.example.finalprojectpam.ui.viewmodel.acara.HomeAcaraViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.InsertAcaraViewModel
import com.example.finalprojectpam.ui.viewmodel.klien.HomeKlienViewModel
import com.example.finalprojectpam.ui.viewmodel.klien.InsertKlienViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory{
        initializer { HomeKlienViewModel(AcaraApplications().container.klienRepository) }
        initializer { InsertKlienViewModel(AcaraApplications().container.klienRepository) }
        initializer { HomeAcaraViewModel(AcaraApplications().container.acaraRepository) }
        initializer { InsertAcaraViewModel(AcaraApplications().container.acaraRepository) }
    }

    fun CreationExtras.AcaraApplications(): AcaraApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as AcaraApplications)
}