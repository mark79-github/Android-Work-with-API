package com.martinbg.androidworkwithapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import com.martinbg.androidworkwithapi.R
import com.martinbg.androidworkwithapi.databinding.FragmentDetailBinding
import com.martinbg.androidworkwithapi.model.Country
import com.martinbg.androidworkwithapi.network.CountryRepository
import com.martinbg.androidworkwithapi.network.CountryService
import com.martinbg.androidworkwithapi.utils.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

//        binding.button.setOnClickListener {
//            val navController = Navigation.findNavController(it)
//            navController.navigateUp()
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countryName = args.name
        (activity as HomeActivity).supportActionBar?.title = countryName

        val countryService = RetrofitHelper.getInstance().create(CountryService::class.java)
        val countryRepository = CountryRepository(countryService)

        countryRepository.getCountryByName(countryName)
            ?.enqueue(object : Callback<List<Country>> {
                override fun onResponse(
                    call: Call<List<Country>>,
                    response: Response<List<Country>>
                ) {
                    val currentCountry = response.body()?.get(0) ?: return
                    binding.apply {
                        capital = getString(R.string.country_capital, currentCountry.capital)
                        region = getString(R.string.country_region, currentCountry.region)
                        population = getString(
                            R.string.country_population,
                            currentCountry.population?.toInt()
                        )
                        area = getString(R.string.country_area, currentCountry.area?.toFloat())
                        Glide
                            .with(view)
                            .load(currentCountry.flags.png)
                            .error(R.drawable.ic_launcher_foreground)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(image)
                    }
                }

                override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                    Snackbar.make(binding.root, "Failed to fetch data", Snackbar.LENGTH_LONG)
                        .show()
                }
            })
    }
}