package com.sfjava.dkquakes.data

import com.squareup.moshi.Json

/*
 * Earthquake Data Model
 *
 * NOTE: this model class is currently *doubling* as both the (network) DTO and "entity" object...
 * which is NOT ideal -- but for this simple example, it will suffice, at least for now :))
 */
data class Earthquake(
    @Json(name = "eqid")
    val id: String,

    @Json(name = "src")
    val source: String,

    val magnitude: Double,
    val depth: Double,

    @Json(name = "lat")
    val latitude: Double,

    @Json(name = "lng")
    val longitude: Double,

    @Json(name = "datetime")
    val datetime: String // FIXME: parse this to Date when converting from JSON?
)
