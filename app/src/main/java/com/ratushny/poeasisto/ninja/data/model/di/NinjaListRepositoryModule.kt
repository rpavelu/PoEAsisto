package com.ratushny.poeasisto.ninja.data.model.di

import com.ratushny.poeasisto.ninja.data.NinjaListRepository
import com.ratushny.poeasisto.ninja.data.NinjaListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface NinjaListRepositoryModule {

    @Binds
    fun bindNinjaListRepository(impl: NinjaListRepositoryImpl) : NinjaListRepository
}