package com.sfjava.dkquakes

import android.content.Context
import com.sfjava.dkquakes.data.Earthquake
import com.sfjava.dkquakes.service.Earthquakes
import com.sfjava.dkquakes.service.EarthquakesService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MockedEarthquakesService(val context: Context): EarthquakesService {
    override suspend fun getEarthquakes(): Earthquakes {

        // build mock earthquake list data from JSON file...
        val moshi = Moshi.Builder()
            // .add(Date::class, Rfc3339DateJsonAdapter().nullSafe()) // FIXME: for datetime field?
            .add(KotlinJsonAdapterFactory())
            .build()

        val listType = Types.newParameterizedType(List::class.java, Earthquake::class.java)
        val adapter: JsonAdapter<List<Earthquake>> = moshi.adapter(listType)
        val file = "mock_earthquakes.json"
        val json = context.assets.open(file).bufferedReader().use{ it.readText()}
        return Earthquakes(adapter.fromJson(json) ?: emptyList())
    }
}
