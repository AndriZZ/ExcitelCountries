package com.andriivanov.excitelcountries.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andriivanov.excitelcountries.R
import com.andriivanov.excitelcountries.data.common.*
import com.andriivanov.excitelcountries.data.post.Country
import com.andriivanov.excitelcountries.databinding.FragmentCountriesListBinding
import com.andriivanov.excitelcountries.ui.recyclerview.adapter.CountryAdapter
import com.andriivanov.excitelcountries.utils.showDialog
import com.andriivanov.excitelcountries.utils.visibleOrGone
import com.andriivanov.excitelcountries.viewmodel.CountriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CountryListFragment : Fragment() {

    private lateinit var binding: FragmentCountriesListBinding

    private val countriesViewModel: CountriesViewModel by viewModel()
    private val countryAdapter: CountryAdapter by lazy {
        CountryAdapter(
            onCountryClick = { country ->
                navigateToCountryDetails(country)
            }
        )
    }
    private val handleCountriesLoaded = Observer<DataState<List<Country>>> { result ->
        binding.progressBar.visibleOrGone(result.isLoading())
        result.onSuccess {
            countryAdapter.submitList(it)
        }
        result.onFailure {
            showDialog(
                title = getString(R.string.error_general),
                message = it.errorMessage ?: when (it.errorType) {
                    ErrorType.Save -> getString(R.string.error_save_api)
                    ErrorType.Retrieve -> getString(R.string.error_retrieve_api)
                },
                onOkayAction = {
                    // retry loading data
                    countriesViewModel.loadCountries()
                }
            )
        }
    }
    private val handleCountriesFiltered = Observer<List<Country>> { countries ->
        binding.tvNoResults.visibleOrGone(countries.isEmpty())
        countryAdapter.submitList(countries)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountriesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // load list of countries
        countriesViewModel.loadCountries()
        // setup observers
        countriesViewModel.onCountriesLoaded.observe(viewLifecycleOwner, handleCountriesLoaded)
        countriesViewModel.onCountriesFiltered.observe(viewLifecycleOwner, handleCountriesFiltered)
        // setup search
        setupSearch()
        // setup RecyclerView
        binding.rvCountries.adapter = countryAdapter
        binding.rvCountries.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun setupSearch() {
        binding.inputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(text: Editable?) {
                val phrase = text.toString().trim()
                onSearchInput(phrase)
            }
        })
        binding.inputContainer.setEndIconOnClickListener {
            countriesViewModel.setSearchPhrase("")
            binding.inputSearch.text?.clear()
        }
    }

    private fun onSearchInput(phrase: String) {
        val isPhraseValid = phrase.isNotBlank() && phrase.length >= 3
        if (isPhraseValid) {
            countriesViewModel.setSearchPhrase(phrase)
        } else {
            countriesViewModel.setSearchPhrase("")
        }
    }

    private fun navigateToCountryDetails(country: Country) {
        val action =
            CountryListFragmentDirections.actionCountryListFragmentToCountryDetailsFragment(country)
        findNavController().navigate(action)
    }
}