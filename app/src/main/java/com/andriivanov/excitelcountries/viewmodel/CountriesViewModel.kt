package com.andriivanov.excitelcountries.viewmodel

import androidx.lifecycle.MutableLiveData
import com.andriivanov.excitelcountries.data.common.DataState
import com.andriivanov.excitelcountries.data.common.getOrNull
import com.andriivanov.excitelcountries.data.post.Country
import com.andriivanov.excitelcountries.repository.CountriesRepository


class CountriesViewModel(
    private val countriesRepository: CountriesRepository
) : BaseViewModel() {
    private val _onCountriesLoaded = MutableLiveData<DataState<List<Country>>>()
    val onCountriesLoaded = _onCountriesLoaded

    private val _onCountriesFiltered = MutableLiveData<List<Country>>()
    val onCountriesFiltered = _onCountriesFiltered

    private var searchPhrase = ""

    fun setSearchPhrase(phrase: String) {
        searchPhrase = phrase
        filterByPhrase(searchPhrase)
    }

    /**
     * Loads all available countries
     */
    fun loadCountries() = launchIO {
        if (onCountriesLoaded.value == null) {
            val result = countriesRepository.loadCountries()
            _onCountriesLoaded.postValue(result)
        }
    }

    /**
     * Filters countries by name and posts result
     */
    private fun filterByPhrase(phrase: String) {
        val allCountries = onCountriesLoaded.value?.getOrNull()
        if (phrase.isBlank()) {
            onCountriesFiltered.value = allCountries
        } else {
            // filter by country name or capital name
            val filteredCountries = allCountries?.filter {
                it.name.contains(phrase, ignoreCase = true) ||
                        it.capitalName.contains(phrase, ignoreCase = true)
            }
            onCountriesFiltered.postValue(filteredCountries)
        }
    }
}


