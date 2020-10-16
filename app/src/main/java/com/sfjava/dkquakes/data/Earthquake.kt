package com.sfjava.dkquakes.data

import com.squareup.moshi.Json

/*
 * Earthquake Data Model
 */
data class Earthquake(
    @Json(name = "eqid")
    val id: String
)
