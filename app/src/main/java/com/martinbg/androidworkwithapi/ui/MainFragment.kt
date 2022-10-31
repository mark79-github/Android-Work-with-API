package com.martinbg.androidworkwithapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.martinbg.androidworkwithapi.databinding.FragmentMainBinding
import com.martinbg.androidworkwithapi.model.Country
import com.martinbg.androidworkwithapi.network.CountryRepository
import com.martinbg.androidworkwithapi.network.CountryService
import com.martinbg.androidworkwithapi.utils.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private var adapter: CountryAdapter = CountryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (adapter.itemCount > 0) {
            binding.recyclerView.adapter = adapter
            return
        }

        val countryService = RetrofitHelper.getInstance().create(CountryService::class.java)
        val countryRepository = CountryRepository(countryService)

        countryRepository.getCountries()?.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                val countries = response.body() ?: return
                adapter = CountryAdapter(countries)
                binding.recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Snackbar.make(binding.root, "Failed to fetch data", Snackbar.LENGTH_LONG)
                    .show()
            }
        })
    }

}