package com.andriivanov.excitelcountries.viewmodel

import androidx.lifecycle.MutableLiveData
import com.andriivanov.excitelcountries.data.common.DataState
import com.andriivanov.excitelcountries.data.post.Country
import com.andriivanov.excitelcountries.repository.CountriesRepository


class CountriesViewModel(
    private val countriesRepository: CountriesRepository
) : BaseViewModel() {
    private val _onCountriesLoaded = MutableLiveData<DataState<List<Country>>>()
    val onCountriesLoaded = _onCountriesLoaded

    /**
     * Loads all available countries
     */
    fun loadCountries() = launchIO {
        if (onCountriesLoaded.value == null) {
            val result = countriesRepository.loadCountries()
            _onCountriesLoaded.postValue(result)
        }
    }
}


