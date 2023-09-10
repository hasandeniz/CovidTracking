package com.hasandeniz.covidtracking.data.remote.dto.statistics

import com.hasandeniz.covidtracking.data.remote.dto.common.Cases
import com.hasandeniz.covidtracking.data.remote.dto.common.Deaths
import com.hasandeniz.covidtracking.data.remote.dto.common.Tests

data class StatisticsResponse(
    val cases: Cases,
    val continent: String,
    val country: String,
    val day: String,
    val deaths: Deaths,
    val population: Int,
    val tests: Tests,
    val time: String
)