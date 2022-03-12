package com.ratushny.poeasisto.league.network

import com.ratushny.poeasisto.league.model.League
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface LeagueService {

    // https://www.pathofexile.com/developer/docs/api-resource-leagues#get
    @GET("leagues")
    suspend fun getLeagueService(
        @Query("compact") type: String
    ): League

    companion object Factory {
        fun create(): LeagueService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://api.pathofexile.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(LeagueService::class.java)
        }
    }
}