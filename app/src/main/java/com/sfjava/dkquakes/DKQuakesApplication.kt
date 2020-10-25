package com.sfjava.dkquakes

import android.app.Application
import com.sfjava.dkquakes.service.EarthquakesService
import com.sfjava.dkquakes.service.GeonamesEarthquakesService

open class DKQuakesApplication: Application() {

    // TODO: inject EarthquakesService appropriately where needed...
    // TODO: i.e. instead of using the Application class to create/provide it here
    open val earthquakesService: EarthquakesService by lazy { GeonamesEarthquakesService() }
}
