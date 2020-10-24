package com.sfjava.dkquakes.service

import com.sfjava.dkquakes.data.Earthquake

class MockEarthquakesService(val mockEarthquakes: List<Earthquake>): EarthquakesService {
    override suspend fun getEarthquakes(): Earthquakes {

        // return the list of mock earthquakes
        return Earthquakes(mockEarthquakes)
    }
}
