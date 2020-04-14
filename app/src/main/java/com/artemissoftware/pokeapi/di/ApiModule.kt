package com.artemissoftware.pokeapi.di

import com.artemissoftware.pokeapi.api.PokemonApi
import com.artemissoftware.pokeapi.service.NetworkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiModule {


    @Provides
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(NetworkService.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

}