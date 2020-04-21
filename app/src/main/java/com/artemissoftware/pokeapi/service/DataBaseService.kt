package com.artemissoftware.pokeapi.service

import com.artemissoftware.pokeapi.models.Note
import io.reactivex.Single

class DataBaseService {


    fun getExampleNotes(): Single<List<Note>> {
        val createdList = mutableListOf<Note>()
        createdList.add(Note("My favourite pokemon", "11-04-2019"))
        createdList.add(Note("I know some secrets about this pokemon", "12-04-2019"))
        createdList.add(Note("Use water to fight it", "14-04-2019"))
        return Single.just(createdList)
    }

}