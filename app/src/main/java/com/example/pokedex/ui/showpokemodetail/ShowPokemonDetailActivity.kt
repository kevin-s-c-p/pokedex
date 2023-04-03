package com.example.pokedex.ui.showpokemodetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedex.databinding.ActivityShowPokemonDetailBinding
import com.example.pokedex.ui.showpokemodetail.ui.ShowPokemonDetailFragment
import com.example.pokedex.utils.BaseActivity

class ShowPokemonDetailActivity : BaseActivity(BLACK) {

    private lateinit var binding: ActivityShowPokemonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.containerDetail.id, ShowPokemonDetailFragment())
            .commitNow()
    }
}