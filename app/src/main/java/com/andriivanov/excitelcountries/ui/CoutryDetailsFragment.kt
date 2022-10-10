package com.andriivanov.excitelcountries.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andriivanov.excitelcountries.R
import com.andriivanov.excitelcountries.data.post.Country
import com.andriivanov.excitelcountries.databinding.FragmentCountryDetailsBinding
import com.andriivanov.excitelcountries.ui.CountryListFragment.Companion.ARG_COUNTRY
import com.andriivanov.excitelcountries.utils.loadUrl
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MarkerOptions


class CountryDetailsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentCountryDetailsBinding
    private lateinit var country: Country

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get country from safe args
        country = requireArguments().getParcelable(ARG_COUNTRY)
            ?: throw IllegalStateException("Country not found!")
        binding.tvPopulation.text = getString(R.string.population, country.population)
        binding.tvCapital.text = getString(R.string.capital, country.capitalName)
        binding.tvRegion.text = getString(R.string.region, country.region)
        binding.ivFlag.loadUrl(country.flagUrl)

        // Get a handle to the fragment and register the callback.
        binding.map.getFragment<SupportMapFragment>().getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.uiSettings.setAllGesturesEnabled(false)
        googleMap.addMarker(
            MarkerOptions()
                .position(country.latLng)
                .title(country.name)
        )
        val cameraPosition = CameraPosition.Builder().target(country.latLng).zoom(5.0f).build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        googleMap.moveCamera(cameraUpdate)
    }
}