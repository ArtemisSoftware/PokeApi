package com.artemissoftware.pokeapi

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.artemissoftware.pokeapi.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {

    var tabLayout: TabLayout? = null
    lateinit var viewPager:ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        //setSupportActionBar(toolbar)
        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        */


        viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(getSupportFragmentManager());

        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout?.setupWithViewPager(viewPager,true);


    }
}
