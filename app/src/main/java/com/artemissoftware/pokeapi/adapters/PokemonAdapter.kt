package com.artemissoftware.pokeapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.artemissoftware.pokeapi.MainActivity
import com.artemissoftware.pokeapi.R
import com.artemissoftware.pokeapi.databinding.ItemPokemonBinding
import com.artemissoftware.pokeapi.models.PokemonResult




class PokemonAdapter(private var registers: ArrayList<PokemonResult>) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {

        val itemPokemonBinding: ItemPokemonBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pokemon,
            parent,
            false
        )
        return PokemonViewHolder(itemPokemonBinding)
    }

    override fun getItemCount(): Int = registers.size


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        holder.itemPokemonBinding.pokemon = registers[position]
        holder.itemPokemonBinding.imageUrl = registers[position].imageUrl
        holder.itemPokemonBinding.shineButtonlistener = MainActivity.ClickHandler()
        holder.itemPokemonBinding.itemListener = MainActivity.ClickHandler()
    }


    fun setUpPokedex(pokemons: List<PokemonResult>) {
        registers.clear()
        registers.addAll(pokemons)
        notifyDataSetChanged()
    }


}