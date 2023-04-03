package com.example.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.RowPokemonPreviewBinding
import com.example.pokedex.items.PoekmonItem
import com.example.pokedex.utils.load

class PokemonPreviewAdapter(
    private val list: List<PoekmonItem>,
    private val callback: (PoekmonItem) -> Unit
): RecyclerView.Adapter<PokemonPreviewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RowPokemonPreviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowPokemonPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imagePokemonPreview.load(list[position].imagePokemon)
        holder.binding.pokemonName.text = list[position].namePokemon
        holder.binding.pokemonDescription.text = list[position].description

        holder.binding.containerRow.setOnClickListener { callback.invoke(list[position]) }
    }
}