package com.andriivanov.excitelcountries.repository

import com.andriivanov.excitelcountries.data.common.DataState
import com.andriivanov.excitelcountries.data.mapper.CountryMapper
import com.andriivanov.excitelcountries.data.post.Country
import com.andriivanov.excitelcountries.networking.CountriesApi


class CountriesRepository(
    private val countriesApi: CountriesApi,
    private val countriesMapper: CountryMapper
) {
    suspend fun loadCountries(): DataState<List<Country>> {
        val result = countriesApi.getCountries()
        return countriesMapper.mapListData(result)
    }
}