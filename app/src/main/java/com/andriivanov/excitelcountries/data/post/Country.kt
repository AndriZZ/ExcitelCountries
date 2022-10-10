package com.andriivanov.excitelcountries.data.post

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val capitalName: String,
    val countryCode: String,
    val flagUrl: String,
    val latLng: LatLng,
    val name: String,
    val population: Long,
    val region: Region,
    val subregion: String
) : Parcelable