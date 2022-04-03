package com.ratushny.poeasisto.di

import com.ratushny.poeasisto.league.LeagueListRepository
import com.ratushny.poeasisto.league.LeagueListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface LeagueListRepositoryModule {

    @Binds
    fun providesLeagueListRepositoryModule(impl: LeagueListRepositoryImpl) : LeagueListRepository
}