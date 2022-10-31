package com.martinbg.androidworkwithapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.martinbg.androidworkwithapi.databinding.CountryListItemBinding

class CountryAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CountryListItemBinding.inflate(layoutInflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val currentCountry = countries[position]
        holder.binding.apply {
            country = currentCountry.name
            capital = currentCountry.capital
            region = currentCountry.region
            population = currentCountry.population
            area = currentCountry.area

//            Glide
//                .with(holder.binding.root.context)
//                .load(currentCountry.flags.png)
//                .centerCrop()
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .into(ivFlag)
        }

        holder.binding.root.setOnClickListener {
            Snackbar.make(it, currentCountry.name, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun getItemCount() = countries.size

    class CountryViewHolder(val binding: CountryListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}