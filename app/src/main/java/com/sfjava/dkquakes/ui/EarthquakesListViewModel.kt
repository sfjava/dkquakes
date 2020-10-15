package com.sfjava.dkquakes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sfjava.dkquakes.data.Earthquake
import com.sfjava.dkquakes.service.EarthquakesApi
import kotlinx.coroutines.launch

class EarthquakesListViewModel : ViewModel() {
    val earthquakes: LiveData<List<Earthquake>>
        get() = _earthquakes

    private val _earthquakes = MutableLiveData<List<Earthquake>>()

    init {
        fetchEarthquakes()
    }

    private fun fetchEarthquakes() {
        println("EarthquakesListViewModel::fetchEarthquakes()")
        viewModelScope.launch {
            _earthquakes.value = EarthquakesApi.earthquakesService.getEarthquakes()
        }
    }
}
