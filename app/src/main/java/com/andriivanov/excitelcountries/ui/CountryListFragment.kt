package com.andriivanov.excitelcountries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
            onCountryClick = { }
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
        // setup RecyclerView
        binding.rvCountries.adapter = countryAdapter
        binding.rvCountries.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
}