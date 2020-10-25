package com.sfjava.dkquakes

import com.sfjava.dkquakes.service.EarthquakesService

class DKQuakesTestApplication: DKQuakesApplication() {

    override val earthquakesService: EarthquakesService by lazy {
        MockedEarthquakesService(applicationContext)
    }
}