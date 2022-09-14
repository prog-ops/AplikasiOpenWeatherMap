package com.openweathermaporg.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openweathermaporg.myapplication.data.dataclasses.currentweather.WeatherResponseRoom
import com.openweathermaporg.myapplication.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repo: RoomRepository): ViewModel() {
    lateinit var mld: MutableLiveData<List<WeatherResponseRoom>>

    init {
        mld = MutableLiveData()
        loadList()
    }

    fun getMutableLiveDataObserver(): MutableLiveData<List<WeatherResponseRoom>> {
        return mld
    }

    private fun loadList() {
        viewModelScope.launch(Dispatchers.IO) {
            mld.postValue(repo.loadList())
        }
    }

    fun insert(item: WeatherResponseRoom) {
        repo.insert(item)
    }

    fun load(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.load(name)
        }
    }
}