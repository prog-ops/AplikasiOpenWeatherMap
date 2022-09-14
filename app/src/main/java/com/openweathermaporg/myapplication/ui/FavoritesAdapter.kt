package com.openweathermaporg.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openweathermaporg.myapplication.data.dataclasses.currentweather.WeatherResponseRoom
import com.openweathermaporg.myapplication.databinding.WeatherItemViewDbBinding

class FavoritesAdapter (private val list: List<WeatherResponseRoom>): RecyclerView.Adapter<FavoritesAdapter.Holder>() {
    inner class Holder(private val b: WeatherItemViewDbBinding): RecyclerView.ViewHolder(b.root){
        fun bind(weatherResponse: WeatherResponseRoom) {
            with(b) {
                cityTV.text = weatherResponse.name
                itemWeatherDescription.text = weatherResponse.desc
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val bind = WeatherItemViewDbBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(bind)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val weatherResponse = list[position]
        holder.bind(weatherResponse)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}