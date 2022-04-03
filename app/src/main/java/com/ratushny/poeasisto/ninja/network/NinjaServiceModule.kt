package com.ratushny.poeasisto.ninja.network

import com.ratushny.poeasisto.NinjaScope
import com.ratushny.poeasisto.di.RetrofitModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named

@Module(includes = [RetrofitModule::class])
interface NinjaServiceModule {

    companion object {
        @Provides
        @NinjaScope
        fun providesNinjaNetworkService(
            @Named("ninjaRetrofit") retrofit: Retrofit
        ): NinjaNetworkService = retrofit.create()
    }

    @Named("ninjaRetrofit")
    @Binds
    @NinjaScope
    fun provideNinjaRetrofit(retrofit: Retrofit): Retrofit
}