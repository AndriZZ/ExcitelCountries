package com.andriivanov.excitelcountries.di

import com.andriivanov.excitelcountries.repository.CountriesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { CountriesRepository(get(),get()) }

}