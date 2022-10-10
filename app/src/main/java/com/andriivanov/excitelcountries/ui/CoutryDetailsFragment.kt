package com.andriivanov.excitelcountries.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andriivanov.excitelcountries.databinding.FragmentCountryDetailsBinding


class CountryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryDetailsBinding.inflate(inflater)
        return binding.root
    }
}