package com.hasandeniz.covidtracking.data.remote.dto.history

import com.hasandeniz.covidtracking.data.remote.dto.common.Cases
import com.hasandeniz.covidtracking.data.remote.dto.common.Deaths
import com.hasandeniz.covidtracking.data.remote.dto.common.Tests

data class HistoryResponse(
    val cases: Cases,
    val continent: String,
    val country: String,
    val day: String,
    val deaths: Deaths,
    val population: Int,
    val tests: Tests,
    val time: String
)