package com.sfjava.dkquakes.service

import com.sfjava.dkquakes.data.Earthquake

/*
 * Example JSON from Earthquake Service:
 * http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2
 *
 * { "earthquakes": [
 *      {
 *         "datetime": "2011-03-11 04:46:23",
 *         "depth": 24.4,
 *         "lng": 142.369,
 *         "src": "us",
 *         "eqid": "c0001xgp",
 *         "magnitude": 8.8,
 *         "lat": 38.322
 *       }, ...
 *   ]
 * }
 *
 */
interface EarthquakesService {
    suspend fun getEarthquakes(): List<Earthquake>
}
