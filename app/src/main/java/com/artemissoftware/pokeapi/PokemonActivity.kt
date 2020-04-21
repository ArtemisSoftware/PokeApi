package com.artemissoftware.pokeapi

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.artemissoftware.pokeapi.adapters.ViewPagerAdapter
import com.artemissoftware.pokeapi.di.DaggerApiComponent
import com.artemissoftware.pokeapi.models.Note
import com.artemissoftware.pokeapi.models.PokemonResult
import com.artemissoftware.pokeapi.viewmodel.PokemonViewModel
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.activity_pokemon.pokemon_fetch_error
import kotlinx.android.synthetic.main.activity_pokemon.pokemon_fetch_progress
import kotlinx.android.synthetic.main.activity_pokemon.tag_group
import kotlinx.android.synthetic.main.fragment_about.*

class PokemonActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager:ViewPager

    private val pokemonViewModel: PokemonViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        DaggerApiComponent.create().inject(this)


        //setSupportActionBar(toolbar)
        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        */


        viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(getSupportFragmentManager());

        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager,true);

        observeLiveData()

        var pokemonId = intent.extras?.getString("id")

        if (pokemonId != null) {
            pokemonViewModel.fetchPokemon(pokemonId)
        }
    }

    private fun observeLiveData() {
        observeInProgress()
        observeIsError()
        observePokemonList()
        observeNotesList()
    }

    private fun observeInProgress() {
        val inProgressLD: LiveData<Boolean> = pokemonViewModel.inProgressMLD

        inProgressLD.observe(this, Observer { isLoading ->
            isLoading.let {
                if (it) {
                    pokemon_fetch_error.visibility = View.GONE
                    pokemon_fetch_progress.visibility = View.VISIBLE
                } else {
                    pokemon_fetch_progress.visibility = View.GONE
                }
            }
        })
    }


    private fun observePokemonList() {

        val pokemonListLD: LiveData<List<PokemonResult>> = pokemonViewModel.pokemonListMLD

        pokemonListLD.observe(this, Observer { pokedex ->
            pokedex.let {

                fillResult(it.get(0))
                (viewPager.adapter as ViewPagerAdapter).updatePokemon(it.get(0))
            }
        })
    }



    private fun observeNotesList() {

        val pokemonListLD: LiveData<List<Note>> = pokemonViewModel.notesListMLD

        pokemonListLD.observe(this, Observer { notes ->
            notes.let {

                (viewPager.adapter as ViewPagerAdapter).updateNotes(it)
            }
        })
    }




    private fun observeIsError() {
        val isErrorLD: LiveData<Boolean> = pokemonViewModel.isErrorMLD
        isErrorLD.observe(this, Observer { isError ->
            isError.let { pokemon_fetch_error.visibility = if (it) View.VISIBLE else View.GONE }
        })
    }


    private fun fillResult(pokemon : PokemonResult){

        txt_fullName.text = pokemon.name
        var list = mutableListOf<String>()

        pokemon.types.forEach { it ->
            list.add(it.type.name)
        }

        tag_group.setTags(list)

        Glide.with(this).load(pokemon.imageUrl).into(crl_img_profile);
    }

}
