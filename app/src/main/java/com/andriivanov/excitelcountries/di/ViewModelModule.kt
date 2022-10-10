package com.andriivanov.excitelcountries.di

import com.andriivanov.excitelcountries.viewmodel.CountriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { CountriesViewModel(get()) }

}