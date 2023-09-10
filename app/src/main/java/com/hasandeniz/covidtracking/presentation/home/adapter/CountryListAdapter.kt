package com.hasandeniz.covidtracking.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hasandeniz.covidtracking.databinding.CountryListItemBinding
import com.hasandeniz.covidtracking.domain.model.Country

class CountryListAdapter :
    ListAdapter<Country, CountryListAdapter.CountryViewHolder>(CountryDiffUtil()) {
    var isItemClicked: ((Country) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val binding =
            CountryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        getItem(position)?.let { country ->
            val binding = holder.binding
            binding.cvCountryItem.setOnClickListener {
                isItemClicked?.invoke(country)
            }
            holder.bind(country)
        }
    }

    class CountryViewHolder(val binding: CountryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Country) {
            binding.country = item
        }
    }

    class CountryDiffUtil : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

}