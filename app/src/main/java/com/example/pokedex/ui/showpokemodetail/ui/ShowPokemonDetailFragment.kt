package com.example.pokedex.ui.showpokemodetail.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentShowPokemonDetailBinding
import com.example.pokedex.items.PoekmonItem
import com.example.pokedex.utils.BaseFragment
import com.example.pokedex.utils.load

class ShowPokemonDetailFragment : BaseFragment<FragmentShowPokemonDetailBinding>() {

    companion object {
        fun newInstance() = ShowPokemonDetailFragment()
    }

    private lateinit var viewModel: ShowPokemonDetailViewModel

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentShowPokemonDetailBinding = FragmentShowPokemonDetailBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item: PoekmonItem = activity?.intent?.getSerializableExtra("item") as PoekmonItem

        binding.imagePokemon.load(item.imagePokemon)
        binding.namePokemon.text = item.namePokemon
        binding.description.text = item.description
        binding.height.text = item.height
        binding.weight.text = item.weight
    }

    override fun initViewModel() {

    }

    override fun observers() {

    }

    override fun generalObservers() {

    }


}