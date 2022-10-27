package com.martinbg.androidworkwithapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {
    @GET("all")
    fun getCountries(): Call<List<Country>>

    @GET("name/{name}")
    fun getCountryByName(@Path("name") name: String): Call<List<Country>>
}