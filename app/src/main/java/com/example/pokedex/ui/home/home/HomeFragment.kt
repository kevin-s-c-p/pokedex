package com.example.pokedex.ui.home.home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.pokedex.adapters.PokemonPreviewAdapter
import com.example.pokedex.data.response.*
import com.example.pokedex.databinding.FragmentHomeBinding
import com.example.pokedex.items.ListPokemonItem
import com.example.pokedex.items.PoekmonItem
import com.example.pokedex.ui.showpokemodetail.ShowPokemonDetailActivity
import com.example.pokedex.utils.*
import com.example.pokedex.utils.interfaces.SetTitle
import kotlin.random.Random

class HomeFragment : BaseFragment<FragmentHomeBinding>(), LocationListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var toolbar: SetTitle
    private var numRandom: Int = 0
    private var distanceInMts = 0.0
    private var lastLocation: Location? = null
    private val listAllPokemon = mutableListOf<ListPokemonItem>()
    private val listPokemon = mutableListOf<PoekmonItem>()
    private lateinit var locationListener: LocationManager

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        toolbar = context as SetTitle
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        findMyLocation()


        binding.viewBottom.buttonShowPokemon.setOnClickListener { showPokemonRandom() }
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        if (WiFi.checkConnection(requireContext())) {
            viewModel.requestPokemon()
        }
    }

    override fun observers() {
        viewModel.response.observe(viewLifecycleOwner) {
            when(it) {
                is PokemonListResponse -> updateListPokemon(it.results)
                is PokemonDetailResponse -> updateListPokemonWatched(it)
                is PokemonDescriptionResponse -> updateDescriptionPokemon(it)
            }
        }
    }

    private fun updateDescriptionPokemon(it: PokemonDescriptionResponse) {
        val description = it.descriptions.find { descr -> descr.language.name == "es" }
        val item = listPokemon.find { pokemon -> pokemon.id == it.id }
        val positionItem = listPokemon.indexOf(item)

        item?.description = description?.description ?: ""
        listPokemon[positionItem] = item!!
        listPokemonEmpty(listPokemon.isEmpty())
        binding.viewBottom.recyclerView.adapter?.notifyItemInserted(listPokemon.lastIndex)
        binding.lastPokemonWatched.load(item.imagePokemon)
        showAlertForPokemon(item)
        setNumOfPokemonsWatched(listPokemon.size)
    }

    private fun setNumOfPokemonsWatched(num: Int) {
        binding.numOfPokemonWatchet.text = num.toString()
    }

    private fun updateListPokemon(it: List<ResultResponse>) {
        it.forEach {  result ->
            listAllPokemon.add(ListPokemonItem(result.name, result.name))
        }
    }

    private fun updateListPokemonWatched(it: PokemonDetailResponse) {
        val item = PoekmonItem(it.id, it.sprites.imagePokemon, it.name, "", it.height.toString(), it.weight.toString())
        listPokemon.add(item)
        viewModel.requestPokemonDescription(numRandom)
    }

    override fun generalObservers() {
        viewModel.progress.observe(viewLifecycleOwner) { showIconLoad(it, childFragmentManager) }
    }

    private fun setUpRecyclerView() {
        binding.viewBottom.recyclerView.adapter = PokemonPreviewAdapter(listPokemon) {
            showAlertForPokemon(it)
        }
    }

    private fun findMyLocation() {
        locationListener = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationListener.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0F, this)
        }

    }

    override fun onPause() {
        super.onPause()
        locationListener.removeUpdates(this)
    }

    override fun onResume() {
        super.onResume()
        findMyLocation()
    }

    override fun onDestroy() {
        super.onDestroy()
        locationListener.removeUpdates(this)
    }

    private fun listPokemonEmpty(isEmpty: Boolean) {
        if (isEmpty) {
            binding.viewBottom.textPokemonsEmpty.visibility = View.VISIBLE
            binding.viewBottom.recyclerView.visibility = View.GONE
        }
        else {
            binding.viewBottom.textPokemonsEmpty.visibility = View.GONE
            binding.viewBottom.recyclerView.visibility = View.VISIBLE
        }
    }

    private fun showPokemonRandom() {
        numRandom = Random.nextInt(0, listAllPokemon.size)
        viewModel.requestPokemonDetail(numRandom)
    }

    private fun showAlertForPokemon(item: PoekmonItem) {
        showMessageDialog(item.namePokemon, item.description, childFragmentManager, item) {
            goDetailPokemon(item)
        }
    }

    private fun goDetailPokemon(item: PoekmonItem) {
        val intent = Intent(activity, ShowPokemonDetailActivity::class.java).apply {
            putExtra("item", item)
        }
        activity?.startActivity(intent)
    }

    override fun onLocationChanged(location: Location) {
        if (lastLocation != null) {
            distanceInMts = location.distanceTo(lastLocation!!).toDouble()
            binding.mtsWalked.text = "${distanceInMts.toInt()} mts"
            validateIfWeWalkTenMts()
            return
        }

        lastLocation = location
    }

    private fun validateIfWeWalkTenMts() {
        if (distanceInMts == 0.0) return

        if (distanceInMts >= 10) {
            showPokemonRandom()
            distanceInMts = 0.0
            lastLocation = null
        }
    }
}