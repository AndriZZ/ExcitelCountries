package com.andriivanov.excitelcountries.ui.recyclerview.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.andriivanov.excitelcountries.data.post.Country
import com.andriivanov.excitelcountries.databinding.ItemCountryBinding
import com.andriivanov.excitelcountries.utils.loadUrl


class CountryViewHolder(
    private val binding: ItemCountryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Country,
        onCountryAction: (item: Country) -> Unit
    ) = with(binding) {
        tvCountryName.text = item.name
        ivFlag.loadUrl(item.flagUrl)
        root.setOnClickListener {
            onCountryAction(item)
        }
    }
}