package com.sfjava.dkquakes

import com.sfjava.dkquakes.data.Earthquake
import com.sfjava.dkquakes.service.Earthquakes
import com.sfjava.dkquakes.service.EarthquakesService

class MockEarthquakesService(val mockEarthquakes: List<Earthquake>): EarthquakesService {
    override suspend fun getEarthquakes(): Earthquakes {

        // return the list of mock earthquakes
        return Earthquakes(mockEarthquakes)
    }
}
