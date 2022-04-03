package com.ratushny.poeasisto.ninja.data.model.di

import com.ratushny.poeasisto.ninja.data.NinjaNetworkConverter
import com.ratushny.poeasisto.ninja.data.NinjaNetworkConverterImpl
import dagger.Binds
import dagger.Module

@Module
interface NinjaNetworkConverterModule {

    @Binds
    fun bindNetworkConverter(impl: NinjaNetworkConverterImpl): NinjaNetworkConverter
}