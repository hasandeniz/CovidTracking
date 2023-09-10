package com.hasandeniz.covidtracking.data.remote.dto.statistics

import com.hasandeniz.covidtracking.domain.model.Country
import com.hasandeniz.covidtracking.domain.model.CountryDetails

data class StatisticDto(
    val response: List<StatisticsResponse>,
    val results: Int
)

fun StatisticDto.toCountry(): List<Country> {
    return response.map { statisticsResponse ->
        Country(
            name = statisticsResponse.country,
            tests = statisticsResponse.tests.total,
            cases = statisticsResponse.cases.total,
            deaths = statisticsResponse.deaths.total
        )
    }
}

fun StatisticDto.toCountryDetails(): List<CountryDetails> {
    return response.map { statisticsResponse ->
        CountryDetails(
            name = statisticsResponse.country,
            cases = statisticsResponse.cases,
            deaths = statisticsResponse.deaths,
            population = statisticsResponse.population,
            tests = statisticsResponse.tests
        )
    }
}
