package com.ratushny.poeasisto.ninja.network

import com.ratushny.poeasisto.ninja.data.model.currencyoverview.NinjaCurrencyResponse
import com.ratushny.poeasisto.ninja.data.model.itemoverview.NinjaItemResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NinjaNetworkService {

    @GET("currencyoverview")
    suspend fun getNinjaCurrencyDataService(
        @Query("league") league: String,
        @Query("type") type: String
    ): NinjaCurrencyResponse

    @GET("itemoverview")
    suspend fun getNinjaItemDataService(
        @Query("league") league: String,
        @Query("type") type: String
    ): NinjaItemResponse

    companion object Factory {
        fun create(): NinjaNetworkService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://poe.ninja/api/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NinjaNetworkService::class.java)
        }
    }
}