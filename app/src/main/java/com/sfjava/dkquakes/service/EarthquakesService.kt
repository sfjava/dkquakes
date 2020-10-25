package com.sfjava.dkquakes.service

import com.sfjava.dkquakes.data.Earthquake

/**
 * Defines the API interface for an Earthquakes Service.
 */
interface EarthquakesService {
    suspend fun getEarthquakes(): Earthquakes
}

// NOTE: need this wrapper data-class since the JSON for the Geonames Earthquakes API response is
// actually an *object* with the list of quakes as it's value... ((sigh))
//
// FIXME: not sure if this should really live in the data-model package or... not :-/
//
data class Earthquakes(val earthquakes: List<Earthquake>)
