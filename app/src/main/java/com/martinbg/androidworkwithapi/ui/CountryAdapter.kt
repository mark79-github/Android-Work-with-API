package com.martinbg.androidworkwithapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.martinbg.androidworkwithapi.R
import com.martinbg.androidworkwithapi.databinding.LayoutListItemBinding
import com.martinbg.androidworkwithapi.model.Country

class CountryAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutListItemBinding.inflate(layoutInflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val currentCountry = countries[position]
        holder.binding.apply {
            country = currentCountry.name
            capital = currentCountry.capital
            Glide
                .with(root.context)
                .load(currentCountry.flags.png)
                .error(R.drawable.ic_launcher_foreground)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivFlag)
        }

        holder.binding.root.setOnClickListener {
            val navController = Navigation.findNavController(it)
            val action =
                MainFragmentDirections.actionMainFragmentToDetailFragment(currentCountry.name)
            navController.navigate(action)
        }
    }

    override fun getItemCount() = countries.size

    class CountryViewHolder(val binding: LayoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}