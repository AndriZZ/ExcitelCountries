package com.andriivanov.excitelcountries.di

import com.andriivanov.excitelcountries.data.mapper.CountryMapper
import org.koin.dsl.module

val appModule = module {
    factory { CountryMapper() }
}