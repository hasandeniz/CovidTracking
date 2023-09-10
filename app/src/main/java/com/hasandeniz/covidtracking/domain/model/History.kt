package com.hasandeniz.covidtracking.domain.model

import com.hasandeniz.covidtracking.data.remote.dto.common.Cases
import com.hasandeniz.covidtracking.data.remote.dto.common.Deaths
import com.hasandeniz.covidtracking.data.remote.dto.common.Tests

data class History(
    val cases: Cases,
    val country: String,
    val day: String,
    val deaths: Deaths,
    val population: Int,
    val tests: Tests,
    val time: String
)

fun History.toCountryDetails(): CountryDetails {
    return CountryDetails(
        name = country,
        cases = cases,
        deaths = deaths,
        population = population,
        tests = tests
    )
}
