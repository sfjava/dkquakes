package com.sfjava.dkquakes.viewmodels

import androidx.lifecycle.*
import com.sfjava.dkquakes.data.Earthquake
import com.sfjava.dkquakes.service.EarthquakesService
import com.sfjava.dkquakes.ui.Event
import kotlinx.coroutines.launch

class EarthquakesListViewModel(
    val earthquakesService: EarthquakesService,
    private val savedStateHandle: SavedStateHandle // unused currently; see notes below :)
) : ViewModel() {

    // NOTE: if view-model needed to save any state, we'd use savedStateHandle (see notes below)
    //
    val earthquakes: LiveData<List<Earthquake>>
        get() = _earthquakes

    private val _earthquakes = MutableLiveData<List<Earthquake>>()

    // UI event for when a list-item is tapped; --> open item detail fragment
    private val _openEarthquakeEvent = MutableLiveData<Event<Earthquake>>()
    val openEarthquakeEvent: LiveData<Event<Earthquake>> = _openEarthquakeEvent

    // called by data-binding in layout
    fun openEarthquakeDetail(earthquake: Earthquake) {
        _openEarthquakeEvent.value = Event(earthquake)
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        // NOTE: if we had any saved-state, we'd get that here too, when initializing... e.g.
        // setFiltering(getSavedFilterType())
        fetchEarthquakes()
    }

    private fun fetchEarthquakes() {
        println("EarthquakesListViewModel::fetchEarthquakes()")
        _isLoading.value = true
        viewModelScope.launch {
            _earthquakes.value = earthquakesService.getEarthquakes().earthquakes
            _isLoading.value = false
        }
    }

    // NOTE: we'd call a function like the following from [init], and use it to call
    // our setter, say, for [setListFilter] -- if we had some UI state such as a list-filter :))
    //
    // private fun getSavedListFilter() : MyListFilterType {
    //    return savedStateHandle.get(LIST_FILTER_SAVED_STATE_KEY) ?: NO_FILTER
    // }
}
