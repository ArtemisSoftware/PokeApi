package com.artemissoftware.pokeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemissoftware.pokeapi.adapters.PokemonAdapter
import com.artemissoftware.pokeapi.di.DaggerApiComponent
import com.artemissoftware.pokeapi.models.PokemonResult
import com.artemissoftware.pokeapi.viewmodel.PokemonViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var pokemonAdapter: PokemonAdapter

    private val pokemonViewModel: PokemonViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerApiComponent.create().inject(this)


        main_swipe_refresh_layout.setOnRefreshListener {
            main_swipe_refresh_layout.isRefreshing = false
            pokemonViewModel.refresh()
        }

        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonAdapter
        }

        observeLiveData()

    }

    private fun observeLiveData() {
        observeInProgress()
        observeIsError()
        observePokemonList()
    }


    private fun observePokemonList() {

        val vehicleListLD: LiveData<List<PokemonResult>> = pokemonViewModel.pokemonListMLD

        vehicleListLD.observe(this, Observer { pokedex ->
            pokedex.let {
                main_recycler_view.visibility = View.VISIBLE
                pokemonAdapter.setUpPokedex(it)
            }
        })
    }


    private fun observeInProgress() {
        val inProgressLD: LiveData<Boolean> = pokemonViewModel.inProgressMLD

        inProgressLD.observe(this, Observer { isLoading ->
            isLoading.let {
                if (it) {
                    pokemon_fetch_error.visibility = View.GONE
                    main_recycler_view.visibility = View.GONE
                    pokemon_fetch_progress.visibility = View.VISIBLE
                } else {
                    pokemon_fetch_progress.visibility = View.GONE
                }
            }
        })
    }


    private fun observeIsError() {
        val isErrorLD: LiveData<Boolean> = pokemonViewModel.isErrorMLD
        isErrorLD.observe(this, Observer { isError ->
            isError.let { pokemon_fetch_error.visibility = if (it) View.VISIBLE else View.GONE }
        })
    }

}
