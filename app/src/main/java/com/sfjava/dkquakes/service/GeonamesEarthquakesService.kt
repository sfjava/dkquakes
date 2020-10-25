package com.sfjava.dkquakes.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Provides an implementation of the [EarthquakesService] backed by data from the HTTP web-service
 * endpoint from "geonames.org".
 *
 * Example JSON from the Geonames Earthquakes Service:
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
class GeonamesEarthquakesService: EarthquakesService {

    private val BASE_URL = "http://api.geonames.org"
    private val FULL_QUERY_URL = "/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2" +
            "&username=mkoppelman&amp;data=02|01|mquiroz@ebay.com|5ed5193dd4be49513f0108d86b9e5d14|46326bff992841a0baca17c16c94ea99|0|0|637377675206102160" +
            "&amp;sdata=fyCDZM9V0nDdQmYFYWdIy2ZhDIMD/sy9nw9lT9/ByeA=&amp;reserved=0"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okhttp())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
        .create(GeonamesEarthquakesApi::class.java)

    private fun okhttp() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }).build()

    override suspend fun getEarthquakes(): Earthquakes {
        return retrofit.getEarthquakes(FULL_QUERY_URL)
    }
}

interface GeonamesEarthquakesApi: EarthquakesService {
    @GET
    suspend fun getEarthquakes(@Url fullUrl: String): Earthquakes
}