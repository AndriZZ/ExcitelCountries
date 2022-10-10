package com.andriivanov.excitelcountries.data.serv

import com.google.gson.annotations.SerializedName

data class CountryServ(
    @SerializedName("capitalName")
    val capitalName: String?,
    @SerializedName("code")
    val countryCode: String?,
    @SerializedName("flag")
    val flagUrl: String?,
    @SerializedName("latLng")
    val latLng: List<Double>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("population")
    val population: Long?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("subregion")
    val subregion: String?
)