package com.example.gempaduar.data.model

import com.google.gson.annotations.SerializedName

data class GempaResponse(
    @SerializedName("Infogempa") val infogempa: InfoGempa?
)

data class InfoGempa(
    @SerializedName("gempa") val gempa: List<Gempa>?
)

data class Gempa(
    val id: Int = 0, // diisi di Repository, dipakai sebagai argumen navigasi
    @SerializedName("Tanggal") val tanggal: String?,
    @SerializedName("Jam") val jam: String?,
    @SerializedName("Coordinates") val coordinates: String?,
    @SerializedName("Magnitude") val magnitude: String?,
    @SerializedName("Kedalaman") val kedalaman: String?,
    @SerializedName("Wilayah") val wilayah: String?,
    @SerializedName("Potensi") val potensi: String?
)