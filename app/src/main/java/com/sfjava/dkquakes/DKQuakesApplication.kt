package com.sfjava.dkquakes

import android.app.Application
import com.sfjava.dkquakes.service.EarthquakesService
import com.sfjava.dkquakes.service.GeonamesEarthquakesService
import com.sfjava.dkquakes.service.MockEarthquakesService

class DKQuakesApplication: Application() {

    // TODO: provide either mock or real service here depending on DEBUG build-type or injection, etc
    // val earthquakesService: EarthquakesService by lazy { MockEarthquakesService(applicationContext) }
    val earthquakesService: EarthquakesService by lazy { GeonamesEarthquakesService(applicationContext) }
}