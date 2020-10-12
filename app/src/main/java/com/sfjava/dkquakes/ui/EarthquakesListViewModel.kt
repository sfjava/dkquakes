package com.sfjava.dkquakes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sfjava.dkquakes.data.Earthquake

class EarthquakesListViewModel : ViewModel() {
    var earthquakes: LiveData<List<Earthquake>> = MutableLiveData(
        listOf(
            Earthquake("0"), Earthquake("1"), Earthquake("2") // FIXME: needs real data
        )
    )
}
