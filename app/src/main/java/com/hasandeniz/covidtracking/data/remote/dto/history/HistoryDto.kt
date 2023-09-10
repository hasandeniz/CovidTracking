package com.hasandeniz.covidtracking.data.remote.dto.history

import com.hasandeniz.covidtracking.domain.model.History

data class HistoryDto(
    val response: List<HistoryResponse>,
    val results: Int
)

fun HistoryDto.toHistory(): List<History> {
    return response.map { historyResponse ->
        History(
            cases = historyResponse.cases,
            country = historyResponse.country,
            day = historyResponse.day,
            deaths = historyResponse.deaths,
            population = historyResponse.population,
            tests = historyResponse.tests,
            time = historyResponse.time
        )
    }

}
