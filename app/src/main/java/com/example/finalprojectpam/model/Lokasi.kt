package com.example.finalprojectpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lokasi(
    @SerialName("id_lokasi") val idlokasi: String,
    @SerialName("nama_lokasi") val namalokasi: String,
    @SerialName("alamat_lokasi") val alamatlokasi: String,
    val kapasitas: String,

    )
