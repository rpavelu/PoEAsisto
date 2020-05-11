package com.ratushny.poeasisto.data.ninja.currency.model.network

import com.ratushny.poeasisto.data.ninja.currency.model.NinjaCurrencyResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NinjaCurrencyService {

    @GET("currencyoverview")
    suspend fun getNinjaDataService(
        @Query("league") league: String,
        @Query("type") type: String
    ): NinjaCurrencyResponse

    companion object Factory {
        fun create(): NinjaCurrencyService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://poe.ninja/api/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NinjaCurrencyService::class.java)
        }
    }
}