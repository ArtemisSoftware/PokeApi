package com.artemissoftware.pokeapi.models

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

data class PokemonResult (var name: String, val url: String, val id: String, val height: String, val weight: String, val types: List<PokemonType>, val sprites: PokemonSprites){

    var imageUrl: String = ""
        get() {
            if (field == null) {

                val pokemonId : String;

                if(id != null){
                    pokemonId = id
                }
                else{

                    val parts = url.split("/")
                    pokemonId = parts[parts.size -2]
                }
                imageUrl = "https://pokeres.bastionbot.org/images/pokemon/$pokemonId.png"
            }
            return field
        }
        private set


    var pokemonId: String = ""
        get() {
            if (field == null) {


                if(id != null){
                    pokemonId = id
                }
                else{

                    val parts = url.split("/")
                    pokemonId = parts[parts.size -2]
                }
            }
            return field
        }
        private set




    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun loadImage(view: CircleImageView, url: String) {

            Glide.with(view.getContext()).load(url).into(view);
        }
    }
}

data class PokemonType(val type : Type)
data class Type(val name : String)
data class PokemonSprites(val back_shiny : String, val front_shiny : String)


