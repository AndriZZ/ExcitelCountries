package com.andriivanov.excitelcountries.networking

import com.andriivanov.excitelcountries.data.common.DataState
import com.andriivanov.excitelcountries.data.serv.CountryServ
import retrofit2.http.GET

interface CountriesApi {
    @GET("/countries")
    suspend fun getCountries(): DataState<List<CountryServ>>
}