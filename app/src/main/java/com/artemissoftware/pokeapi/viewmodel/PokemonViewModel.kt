package com.artemissoftware.pokeapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artemissoftware.pokeapi.di.DaggerApiComponent
import com.artemissoftware.pokeapi.models.PokemonResult
import com.artemissoftware.pokeapi.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonViewModel : ViewModel() {

    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var pokemonListMLD: MutableLiveData<List<PokemonResult>>

    @Inject
    lateinit var inProgressMLD: MutableLiveData<Boolean>

    @Inject
    lateinit var isErrorMLD: MutableLiveData<Boolean>



    init {
        DaggerApiComponent.create().inject(this)
        fetchPokedex()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun refresh(){
        fetchPokedex()
    }


    private fun fetchPokedex() {

        compositeDisposable.add( // API call get stored in compositeDisposable

            networkService.fetchPokedex() // Makes the call to the endpoint
                .subscribeOn(Schedulers.io()) // Subscribes on a background thread, which is Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) // Displays the result on the main thread (UI thread)
                .map { it.results } // Takes the list of pokemons in PokedexResult pass it on to the next operator
                .subscribeWith(createPokemonObserver()) // The glue that connects networkService.fetchVehicle() with createVehicleObserver()

        )
    }


    private fun createPokemonObserver(): DisposableSingleObserver<List<PokemonResult>> {
        return object : DisposableSingleObserver<List<PokemonResult>>() {

            override fun onSuccess(vehicles: List<PokemonResult>) {
                inProgressMLD.value = true
                isErrorMLD.value = false
                pokemonListMLD.value = vehicles
                inProgressMLD.value = false
            }

            override fun onError(e: Throwable) {
                inProgressMLD.value = true
                isErrorMLD.value = true
                Log.e("onError()", "Error: ${e.message}")
                inProgressMLD.value = false
            }
        }
    }



}