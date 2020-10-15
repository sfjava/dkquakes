package com.sfjava.dkquakes.service

object EarthquakesApi {
    val earthquakesService: EarthquakesService by lazy { MockEarthquakesService() }
}
