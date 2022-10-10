package com.andriivanov.excitelcountries.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.andriivanov.excitelcountries.R
import com.andriivanov.excitelcountries.data.post.Country
import com.andriivanov.excitelcountries.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // inflate binding
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        // setup navController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        navController.addOnDestinationChangedListener(this)
        binding.toolbar.setupWithNavController(navController)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        binding.toolbarTitle.text = when (destination.id) {
            R.id.countryListFragment -> getString(R.string.countries)
            R.id.countryDetailsFragment -> {
                val country = arguments?.getParcelable(CountryListFragment.ARG_COUNTRY) as Country?
                    ?: throw IllegalStateException("Country not found!")
                country.name
            }
            else -> ""
        }
    }
}