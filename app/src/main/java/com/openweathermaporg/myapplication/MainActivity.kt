package com.openweathermaporg.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.openweathermaporg.myapplication.databinding.ActivityMainBinding
import com.openweathermaporg.myapplication.ui.CurrentWeatherFragment
import com.openweathermaporg.myapplication.ui.FavoritesFragment
import com.openweathermaporg.myapplication.usages.MyFragmentStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val b: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        val list = arrayListOf(
            CurrentWeatherFragment(),
            FavoritesFragment.newInstance("Fragment Favorit")
        )
        b.pager.adapter = MyFragmentStateAdapter(this, list)


        TabLayoutMediator(b.tabLayout, b.pager) { tab, position ->
            when (position) {
                0 -> tab.text = "Search"
                1 -> tab.text = "Favorites"
            }
        }.attach()
    }
}