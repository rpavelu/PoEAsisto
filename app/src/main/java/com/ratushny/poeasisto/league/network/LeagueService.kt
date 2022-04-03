package com.ratushny.poeasisto.league.network

import com.ratushny.poeasisto.league.model.LeagueList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface LeagueService {

    @GET("leagues")
    suspend fun getLeagueService(
        @Query("compact") type: Boolean
    ): LeagueList
}