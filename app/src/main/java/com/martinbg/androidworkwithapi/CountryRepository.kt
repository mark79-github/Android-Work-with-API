package com.martinbg.androidworkwithapi

import retrofit2.Call

class CountryRepository(private val countryService: CountryService) {
    fun getCountries(): Call<List<Country>>? {
        return try {
            countryService.getCountries()
        } catch (e: Exception) {
            // an error occurred, handle and act accordingly
            null
        }
    }

    fun getCountryByName(name: String): Call<List<Country>>? {
        return try {
            countryService.getCountryByName(name)
        } catch (e: Exception) {
            // an error occurred, handle and act accordingly
            null
        }
    }
}