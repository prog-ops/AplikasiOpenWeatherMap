package com.openweathermaporg.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openweathermaporg.myapplication.data.dataclasses.currentweather.WeatherResponse
import com.openweathermaporg.myapplication.data.dataclasses.fivedayweather.FiveDayWeatherResponse
import com.openweathermaporg.myapplication.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(private val repo: WeatherRepository) :
    ViewModel() {
    var currentWeather: MutableLiveData<WeatherResponse> = MutableLiveData<WeatherResponse>()

    fun getNewWeather(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currentWeather.postValue(repo.getCurrentWeather(cityName))
        }
    }

    var next3Weather: MutableLiveData<FiveDayWeatherResponse> =
        MutableLiveData<FiveDayWeatherResponse>()

    var cityName: MutableLiveData<String> = MutableLiveData()

    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            next3Weather.postValue(repo.getFiveDayWeather(cityName.value!!))
        }
    }

    fun setCityName(city: String) {
        cityName.value = city
    }
}