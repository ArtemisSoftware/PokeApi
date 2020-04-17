package com.artemissoftware.pokeapi.models

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

data class PokemonResult (val name: String, val url: String, val id: String){


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


    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun loadImage(view: CircleImageView, url: String) {

            Glide.with(view.getContext()).load(url).into(view);
        }
    }

}



