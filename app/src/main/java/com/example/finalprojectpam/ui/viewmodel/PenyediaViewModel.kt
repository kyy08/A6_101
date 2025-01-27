package com.example.finalprojectpam.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalprojectpam.AcaraApplications
import com.example.finalprojectpam.ui.viewmodel.acara.AcaraDetailViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.AcaraHomeViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.AcaraInsertViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.AcaraUpdateViewModel
import com.example.finalprojectpam.ui.viewmodel.klien.KlienDetailViewModel
import com.example.finalprojectpam.ui.viewmodel.klien.KlienHomeViewModel
import com.example.finalprojectpam.ui.viewmodel.klien.KlienInsertViewModel
import com.example.finalprojectpam.ui.viewmodel.klien.KlienUpdateViewModel
import com.example.finalprojectpam.ui.viewmodel.lokasi.LokasiDetailViewModel
import com.example.finalprojectpam.ui.viewmodel.lokasi.LokasiHomeViewModel
import com.example.finalprojectpam.ui.viewmodel.lokasi.LokasiInsertViewModel
import com.example.finalprojectpam.ui.viewmodel.lokasi.LokasiUpdateViewModel
import com.example.finalprojectpam.ui.viewmodel.vendor.VendorDetailViewModel
import com.example.finalprojectpam.ui.viewmodel.vendor.VendorHomeViewModel
import com.example.finalprojectpam.ui.viewmodel.vendor.VendorInsertViewModel
import com.example.finalprojectpam.ui.viewmodel.vendor.VendorUpdateViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { LokasiHomeViewModel(AcaraApplications().container.lokasiRepository) }
        initializer { LokasiInsertViewModel(AcaraApplications().container.lokasiRepository) }
        initializer { LokasiDetailViewModel(AcaraApplications().container.lokasiRepository) }
        initializer { LokasiUpdateViewModel(AcaraApplications().container.lokasiRepository) }

        initializer { VendorHomeViewModel(AcaraApplications().container.vendorRepository) }
        initializer { VendorInsertViewModel(AcaraApplications().container.vendorRepository) }
        initializer { VendorDetailViewModel(AcaraApplications().container.vendorRepository) }
        initializer { VendorUpdateViewModel(AcaraApplications().container.vendorRepository) }

        initializer { KlienHomeViewModel(AcaraApplications().container.klienRepository) }
        initializer { KlienInsertViewModel(AcaraApplications().container.klienRepository) }
        initializer { KlienDetailViewModel(AcaraApplications().container.klienRepository) }
        initializer { KlienUpdateViewModel(AcaraApplications().container.klienRepository) }

        initializer { AcaraHomeViewModel(AcaraApplications().container.acaraRepository) }
        initializer { AcaraInsertViewModel(AcaraApplications().container.acaraRepository) }
        initializer { AcaraDetailViewModel(AcaraApplications().container.acaraRepository) }
        initializer { AcaraUpdateViewModel(AcaraApplications().container.acaraRepository) }
    }

    fun CreationExtras.AcaraApplications(): AcaraApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AcaraApplications)
}