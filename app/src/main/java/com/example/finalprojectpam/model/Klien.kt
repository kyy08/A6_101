package com.example.finalprojectpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Klien(
    @SerialName("id_klien") val idklien: String,
    @SerialName("nama_klien") val namaklien : String,
    @SerialName("kontak_klien") val kontakklien: String,
)

