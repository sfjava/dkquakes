package com.sfjava.dkquakes.service

import com.sfjava.dkquakes.data.Earthquake

class MockEarthquakesService: EarthquakesService {
    override suspend fun getEarthquakes(): List<Earthquake> {
        return (0..30).map { Earthquake(it.toString()) }.toList() // mock 30 earthquakes :))
    }
}
