package com.example.pokedex

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.utils.BaseActivity
import com.example.pokedex.utils.interfaces.SetTitle

class MainActivity : BaseActivity(WHITE), SetTitle {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentNav = NavHostFragment.create(R.navigation.navigation_home)
        supportFragmentManager.beginTransaction()
            .replace(binding.containerHome.id, fragmentNav)
            .setPrimaryNavigationFragment(fragmentNav)
            .commitNow()

        binding.toolbar.buttonBack.setOnClickListener {  }
    }

    override fun setTitleInToolbar(title: String) {
        binding.toolbar.title.text = title
    }

    override fun hideToolbar() {
        binding.toolbar.toolbarContainer.visibility = View.GONE
    }

    override fun showToolbar() {
        binding.toolbar.toolbarContainer.visibility = View.VISIBLE
    }

    override fun hideButtonBack() {
        binding.toolbar.buttonBack.visibility = View.GONE
    }
}