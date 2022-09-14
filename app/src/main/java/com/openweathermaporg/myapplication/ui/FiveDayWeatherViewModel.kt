package com.openweathermaporg.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openweathermaporg.myapplication.data.dataclasses.fivedayweather.FiveDayWeatherResponse
import com.openweathermaporg.myapplication.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FiveDayWeatherViewModel @Inject constructor(private val dataSource: WeatherRepository): ViewModel() {
    var currentWeather: MutableLiveData<FiveDayWeatherResponse> = MutableLiveData<FiveDayWeatherResponse>()

    var cityName: MutableLiveData<String> = MutableLiveData()

    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            currentWeather.postValue(dataSource.getFiveDayWeather(cityName.value!!))
        }
    }

    fun setCityName(city: String) {
        cityName.value = city
    }
}