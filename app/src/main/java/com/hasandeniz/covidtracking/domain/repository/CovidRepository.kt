package com.hasandeniz.covidtracking.domain.repository

import com.hasandeniz.covidtracking.data.remote.dto.history.HistoryDto
import com.hasandeniz.covidtracking.data.remote.dto.statistics.StatisticDto
import retrofit2.Response

interface CovidRepository {
    suspend fun getStatistics(searchQuery: String?): Response<StatisticDto>

    suspend fun getHistory(country: String): Response<HistoryDto>
}