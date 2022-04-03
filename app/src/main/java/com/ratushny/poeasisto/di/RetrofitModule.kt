package com.ratushny.poeasisto.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    fun provideRetrofit(url: RetrofitUrl): Retrofit = Retrofit.Builder()
        .baseUrl(url.url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}