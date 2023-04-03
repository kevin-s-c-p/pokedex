package com.example.pokedex.ui.home.screenpermissions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPermissionDeniedBinding
import com.example.pokedex.utils.BaseFragment
import com.example.pokedex.utils.interfaces.SetTitle

class PermissionDeniedFragment : BaseFragment<FragmentPermissionDeniedBinding>() {

    companion object {
        fun newInstance() = PermissionDeniedFragment()
    }

    private lateinit var viewModel: PermissionDeniedViewModel
    private lateinit var toolbar: SetTitle

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPermissionDeniedBinding = FragmentPermissionDeniedBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        toolbar = context as SetTitle
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.hideToolbar()
        weHavePermission(view)

        binding.buttonRequestPermission.setOnClickListener { requestPermissionAgain() }
    }

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        it.forEach { permission ->
            if (!permission.value) {
                Toast.makeText(requireContext(), "Requiere permisos de ubicacion", Toast.LENGTH_SHORT).show()
                return@registerForActivityResult
            }
        }

        view?.findNavController()?.navigate(R.id.action_permissions_fragment_to_home_fragment)
    }

    private fun weHavePermission(view: View) {
        when (PackageManager.PERMISSION_GRANTED) {
            activity?.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                view.findNavController().navigate(R.id.action_permissions_fragment_to_home_fragment)
            }
            else -> {
                requestPermission.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
            }
        }
    }

    private fun requestPermissionAgain() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", requireContext().packageName, null)
        }

        activity?.startActivity(intent)
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this)[PermissionDeniedViewModel::class.java]
    }

    override fun observers() {

    }

    override fun generalObservers() {

    }
}