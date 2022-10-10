package com.andriivanov.excitelcountries.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.andriivanov.excitelcountries.data.post.Country
import com.andriivanov.excitelcountries.databinding.ItemCountryBinding
import com.andriivanov.excitelcountries.ui.recyclerview.viewholder.CountryViewHolder

class CountryAdapter(
    private val onCountryClick: (item: Country) -> Unit
) : ListAdapter<Country, CountryViewHolder>(DIFFER) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binder = ItemCountryBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binder)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(currentList[position], onCountryClick)
    }

    override fun getItemCount(): Int = currentList.size

    companion object {

        private val DIFFER = object : DiffUtil.ItemCallback<Country>() {

            override fun areItemsTheSame(
                oldItem: Country,
                newItem: Country
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: Country,
                newItem: Country
            ): Boolean = oldItem == newItem

        }
    }
}