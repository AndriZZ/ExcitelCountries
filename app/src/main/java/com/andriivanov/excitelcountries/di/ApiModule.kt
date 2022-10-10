package com.andriivanov.excitelcountries.di

import com.andriivanov.excitelcountries.networking.CountriesApi
import retrofit2.Retrofit

class ApiModule {

    companion object {

        fun provideCountriesApi(retrofit: Retrofit): CountriesApi {
            return retrofit.create(CountriesApi::class.java)
        }
    }
}