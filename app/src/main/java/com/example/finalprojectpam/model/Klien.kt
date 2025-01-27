package com.example.finalprojectpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Klien(
   val id_klien: String,
   val nama_klien : String,
   val kontak_klien: String,
)

