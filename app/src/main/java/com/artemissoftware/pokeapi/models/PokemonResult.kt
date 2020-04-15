package com.artemissoftware.pokeapi.models

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

data class PokemonResult (val name: String, val url: String){


    var imageUrl: String = ""
        get() {
            if (field == null) {
                val parts = url.split("/")
                var id = parts[parts.size -2]
                imageUrl = "https://pokeres.bastionbot.org/images/pokemon/$id.png"
            }
            return field
        }
        private set


    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun loadImage(view: CircleImageView, url: String) {

            Glide.with(view.getContext())
                .load(url)
                .into(view);
        }
    }

}



