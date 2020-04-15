package com.artemissoftware.pokeapi.di

import androidx.lifecycle.MutableLiveData
import com.artemissoftware.pokeapi.adapters.PokemonAdapter
import com.artemissoftware.pokeapi.api.PokemonApi
import com.artemissoftware.pokeapi.models.PokemonResult
import com.artemissoftware.pokeapi.service.NetworkService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
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


    @Provides
    fun provideNetworkService(): NetworkService {
        return NetworkService()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideLiveDataListOfPokemons(): MutableLiveData<List<PokemonResult>> {
        return MutableLiveData()
    }

    @Provides
    fun provideLiveDataBoolean(): MutableLiveData<Boolean> {
        return MutableLiveData()
    }


    @Provides
    fun providePokemonList(): ArrayList<PokemonResult> {
        return ArrayList()
    }

    /*
    @Provides
    fun providePokemonAdapter(pokemons: ArrayList<PokemonResult>): PokemonAdapter {
        return PokemonAdapter(pokemons)
    }
    */
}