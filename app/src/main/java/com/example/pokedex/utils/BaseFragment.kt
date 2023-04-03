package com.example.pokedex.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<BINDING: ViewBinding>(): Fragment() {
    private var _binding: BINDING? = null
    protected val binding get() = _binding!!


    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): BINDING
    abstract fun initViewModel()
    abstract fun observers()
    abstract fun generalObservers()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        bindListener()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun bindListener() {
        initViewModel()
        observers()
        generalObservers()
    }
}

