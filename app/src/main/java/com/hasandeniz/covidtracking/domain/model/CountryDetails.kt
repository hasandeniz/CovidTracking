package com.hasandeniz.covidtracking.domain.model

import com.hasandeniz.covidtracking.data.remote.dto.common.Cases
import com.hasandeniz.covidtracking.data.remote.dto.common.Deaths
import com.hasandeniz.covidtracking.data.remote.dto.common.Tests

data class CountryDetails(
    val name: String,
    val cases: Cases,
    val deaths: Deaths,
    val population: Int,
    val tests: Tests
)

