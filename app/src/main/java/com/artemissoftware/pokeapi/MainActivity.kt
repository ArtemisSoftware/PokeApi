package com.artemissoftware.pokeapi

import android.content.Intent
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
import com.sackcentury.shinebuttonlib.ShineButton
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var pokemonAdapter: PokemonAdapter

    private val pokemonViewModel: PokemonViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerApiComponent.create().inject(this)

        pokemonAdapter = PokemonAdapter(ArrayList() , { pokemon -> showPokemon(pokemon) })

        main_swipe_refresh_layout.setOnRefreshListener {
            main_swipe_refresh_layout.isRefreshing = false
            pokemonViewModel.refresh()
        }

        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonAdapter
        }

        observeLiveData()

        pokemonViewModel.refresh()
    }

    private fun observeLiveData() {
        observeInProgress()
        observeIsError()
        observePokemonList()
    }


    private fun observePokemonList() {

        val pokemonListLD: LiveData<List<PokemonResult>> = pokemonViewModel.pokemonListMLD

        pokemonListLD.observe(this, Observer { pokedex ->
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


    private fun showPokemon(pokemon: PokemonResult) {

        val intent = Intent(this, PokemonActivity::class.java)
        intent.putExtra("id", pokemon.pokemonId)
        startActivity(intent)
    }


    class ClickHandler {
        fun onShineButtonClick(view: View) {

            val checked = (view as ShineButton).isChecked
        }


    }

}
