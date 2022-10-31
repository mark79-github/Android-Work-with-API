package com.martinbg.androidworkwithapi.network

import com.martinbg.androidworkwithapi.model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryService {
    @GET("all")
    fun getCountries(): Call<List<Country>>

    @GET("name/{name}")
    fun getCountryByName(@Path("name") name: String, @Query("fullText") fullText: Boolean? = true): Call<List<Country>>
}