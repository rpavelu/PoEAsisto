package com.ratushny.poeasisto.di

import com.ratushny.poeasisto.LeagueScope
import com.ratushny.poeasisto.league.network.LeagueService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named

@Module
interface RetrofitLeagueServiceModule {

    companion object {
        @Provides
        fun providesRetrofitLeagueServiceModule(@Named("leagueRetrofit") retrofit: Retrofit): LeagueService =
            retrofit.create()
    }

    @Named("leagueRetrofit")
    @Binds
    @LeagueScope
    fun provideLeagueRetrofit(retrofit: Retrofit): Retrofit
}