package com.openweathermaporg.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.openweathermaporg.myapplication.data.dataclasses.currentweather.WeatherResponseRoom
import com.openweathermaporg.myapplication.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var b: FragmentFavoritesBinding
    private val vm: FavoritesViewModel by viewModels()

    private lateinit var favoritesAdapter: FavoritesAdapter
    private var arrayList: ArrayList<WeatherResponseRoom> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentFavoritesBinding.inflate(layoutInflater, container, false)

        vm.getMutableLiveDataObserver().observe(viewLifecycleOwner) {
            arrayList = it as ArrayList<WeatherResponseRoom>

            favoritesAdapter = FavoritesAdapter(arrayList)
            b.rv.adapter = favoritesAdapter
            b.rv.layoutManager = LinearLayoutManager(requireActivity())
            b.rv.setHasFixedSize(true)

            it.forEach { item ->
                println("Item: ${item.id} ${item.name} ${item.desc}")
            }
        }

        return b.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            FavoritesFragment().apply {

            }
    }
}