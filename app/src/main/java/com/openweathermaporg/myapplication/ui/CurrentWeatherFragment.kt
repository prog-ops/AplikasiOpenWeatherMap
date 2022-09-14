package com.openweathermaporg.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.openweathermaporg.myapplication.R
import com.openweathermaporg.myapplication.data.dataclasses.currentweather.WeatherResponseRoom
import com.openweathermaporg.myapplication.databinding.FragmentCurrentWeatherBinding
import com.openweathermaporg.myapplication.usages.NetworkUsage
import com.openweathermaporg.myapplication.usages.NetworkUsage.hasNetwork
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

private const val TAG = "MyActivity"

@AndroidEntryPoint
class CurrentWeatherFragment: Fragment() {
    private var _binding: FragmentCurrentWeatherBinding? = null
    private val b get() = _binding!!

    private val viewModel: CurrentWeatherViewModel by viewModels()
    private val vmFav: FavoritesViewModel by viewModels()

    @Inject
    lateinit var adapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)

        b.submitButton.setOnClickListener {
            if (hasNetwork(requireActivity()) == true) {
                if (b.searchingField.text!!.isNotEmpty()) {
                    viewModel.getNewWeather(b.searchingField.text.toString())
                    b.searchingField.isCursorVisible = false
                    b.submitButton.isEnabled = false


                    viewModel.setCityName(b.searchingField.text.toString())
                    viewModel.cityName.observe(viewLifecycleOwner) {
                        viewModel.getWeather()
                    }
                    b.fiveDayRecyclerview.adapter = adapter
                    viewModel.next3Weather.observe(viewLifecycleOwner) { weather ->
                        weather.let {
                            adapter.submitList(weather.list)
                        }
                    }
                }
            } else {
                b.linearHasil.visibility = View.INVISIBLE
                b.fiveDayRecyclerview.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), "Tidak ada jaringan", Toast.LENGTH_SHORT).show()
            }
        }

        b.favoriteButton.setOnClickListener {
            val item = WeatherResponseRoom(id = Random.nextInt(10000), name = b.searchingField.text.toString(), desc = "")
            vmFav.insert(item)
            b.icon.rotation = 2F * 360
            Toast.makeText(requireActivity(), "Added to favotire", Toast.LENGTH_SHORT).show()
        }

        viewModel.currentWeather.observe(viewLifecycleOwner) { weather ->
            if (weather != null) {
                when (weather.weather[0].icon) {
                    "01d", "01n" -> Glide.with(b.icon.context).load(R.drawable.ic_clear_day).into(b.icon)
                    "02d", "02n" -> Glide.with(b.icon.context).load(R.drawable.ic_few_clouds).into(b.icon)
                    "03d", "03n" -> Glide.with(b.icon.context).load(R.drawable.ic_mostly_cloudy).into(b.icon)
                    "04d", "04n" -> Glide.with(b.icon.context).load(R.drawable.ic_broken_clouds).into(b.icon)
                    "09d", "09n" -> Glide.with(b.icon.context).load(R.drawable.ic_snow_weather).into(b.icon)
                    "10d", "10n" -> Glide.with(b.icon.context).load(R.drawable.ic_rainy_weather).into(b.icon)
                    "11d", "11n" -> Glide.with(b.icon.context).load(R.drawable.ic_storm_weather).into(b.icon)
                    "13d", "13n" -> Glide.with(b.icon.context).load(R.drawable.ic_snow_weather).into(b.icon)
                    "50d", "50n" -> Glide.with(b.icon.context).load(R.drawable.ic_cloudy_weather).into(b.icon)
                }
                b.country.text = weather.name
                var string = weather.main?.temp.toString() + "°C"
                b.temperature.text = (string)
                string = weather.weather[0].main + weather.weather[0].description
                b.weatherDescription.text = (string)
                string =
                    "Max: " + weather.main.tempMax.toString() + "°C / Min: " + weather.main?.tempMin + "°C"
                b.maxMinTemperature.text = (string)
                string =
                    "Pressure: " + weather.main.pressure.toString() + " hPa\nHumidity: " + weather.main?.humidity.toString() + "%\nWind speed: " + weather.wind?.speed.toString() + " meter/sec\nClouds: " + weather.clouds?.all.toString() + "%"
                b.otherInformation.text = string
                b.linearHasil.visibility = View.VISIBLE
                b.fiveDayRecyclerview.visibility = View.VISIBLE
                b.submitButton.isEnabled = true
            }
        }
        return b.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Current weather"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}