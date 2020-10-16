package com.sfjava.dkquakes.service

import android.content.Context
import com.sfjava.dkquakes.data.Earthquake
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MockEarthquakesService(val context: Context): EarthquakesService {
    override suspend fun getEarthquakes(): List<Earthquake> {

        // a list of hard-coded, mock earthquakes
        // return (0..30).map { Earthquake(it.toString()) }.toList() // i.e. mock 30 earthquakes :))

        // or, build mock earthquake list data from JSON file
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val listType = Types.newParameterizedType(List::class.java, Earthquake::class.java)
        val adapter: JsonAdapter<List<Earthquake>> = moshi.adapter(listType)
        val file = "mock_earthquakes.json"
        val json = context.assets.open(file).bufferedReader().use{ it.readText()}
        // val json = """[ {"id": "00"}, {"id": "01"}, {"id": "02"}, {"id": "03"}, {"id": "04"} ]"""

        return adapter.fromJson(json) ?: emptyList()
    }
}
