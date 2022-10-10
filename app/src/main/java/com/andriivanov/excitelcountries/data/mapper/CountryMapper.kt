package com.andriivanov.excitelcountries.data.mapper

import com.andriivanov.excitelcountries.data.common.DataState
import com.andriivanov.excitelcountries.data.post.Country
import com.andriivanov.excitelcountries.data.post.Region
import com.andriivanov.excitelcountries.data.serv.CountryServ
import com.google.android.gms.maps.model.LatLng

class CountryMapper : IMapper<CountryServ, Country> {

    override fun map(serv: CountryServ?): Country {
        return Country(
            capitalName = serv?.capitalName ?: "",
            countryCode = serv?.countryCode ?: "",
            flagUrl = serv?.flagUrl ?: "",
            latLng = LatLng(serv?.latLng?.getOrNull(0) ?: -1.0, serv?.latLng?.getOrNull(1) ?: -1.0),
            name = serv?.name ?: "",
            population = serv?.population ?: -1L,
            region = Region.fromValue(serv?.region ?: ""),
            subregion = serv?.subregion ?: ""
        )
    }

    override fun mapListData(serv: DataState<List<CountryServ>>): DataState<List<Country>> {
        return when (serv) {
            is DataState.Loading -> DataState.Loading()
            is DataState.Failure -> DataState.Failure(serv.error)
            is DataState.Success -> {
                val mapped = serv.result.map { map(it) }.sortedByDescending { it.population }
                DataState.Success(mapped)
            }
        }
    }
}