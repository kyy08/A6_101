package com.example.finalprojectpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vendor(
    @SerialName("id_vendor") val idvendor: String,
    @SerialName("nama_vendor") val namavendor: String,
    @SerialName("jenis_vendor") val jenisvendor: String,
    @SerialName("kontak_vendor") val kontakvendor: String,
)
