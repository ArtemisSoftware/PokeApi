package com.artemissoftware.pokeapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.artemissoftware.pokeapi.R
import com.artemissoftware.pokeapi.databinding.ItemNoteBinding
import com.artemissoftware.pokeapi.models.Note

class NoteAdapter(private var registers: ArrayList<Note>/*, private var mListener: (Note) -> Unit*/) : RecyclerView.Adapter<NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val itemNoteBinding: ItemNoteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_note,
            parent,
            false
        )
        return NoteViewHolder(itemNoteBinding)
    }

    override fun getItemCount(): Int = registers.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.itemNoteBinding.note = registers[position]

    }


    fun addRegistes(registes: List<Note>) {
        registers.clear()
        registers.addAll(registes)
        notifyDataSetChanged()
    }


}