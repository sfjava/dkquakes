package com.sfjava.dkquakes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class EarthquakeDetailViewModel(
    private val savedStateHandle: SavedStateHandle // unused
) : ViewModel() {

    // TODO: load earthquake details... from... ??
    //
//    val earthquake: LiveData<Earthquake>
//        get() = _earthquake
//
//    private val _earthquake = MutableLiveData<Earthquake>()

    val earthquakeId: LiveData<String>
        get() = _earthquakeId

    private val _earthquakeId = MutableLiveData<String>()

    fun loadEarthquakeDetails(earthquakeId: String) {
        _earthquakeId.value = earthquakeId
    }
}
