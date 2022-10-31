package com.martinbg.androidworkwithapi.network

import android.util.Log
import com.martinbg.androidworkwithapi.model.Country
import retrofit2.Call

class CountryRepository(private val countryService: CountryService) {
    fun getCountries(): Call<List<Country>>? {
        return try {
            countryService.getCountries()
        } catch (e: Exception) {
            Log.e("Error", "getCountries()")
            null
        }
    }

    fun getCountryByName(name: String): Call<List<Country>>? {
        return try {
            countryService.getCountryByName(name)
        } catch (e: Exception) {
            Log.e("Error", "getCountryByName()")
            null
        }
    }
}