package com.example.finalprojectpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lokasi(
    val id_lokasi: String,
    val nama_lokasi: String,
    val alamat_lokasi: String,
    val kapasitas: String,

    )
