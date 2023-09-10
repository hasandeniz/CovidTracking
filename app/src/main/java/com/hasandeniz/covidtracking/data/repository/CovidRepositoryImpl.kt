package com.hasandeniz.covidtracking.data.repository

import com.hasandeniz.covidtracking.data.remote.dto.CovidApi
import com.hasandeniz.covidtracking.data.remote.dto.history.HistoryDto
import com.hasandeniz.covidtracking.data.remote.dto.statistics.StatisticDto
import com.hasandeniz.covidtracking.domain.repository.CovidRepository
import retrofit2.Response
import javax.inject.Inject

class CovidRepositoryImpl @Inject constructor(private val api: CovidApi) : CovidRepository {
    override suspend fun getStatistics(searchQuery: String?): Response<StatisticDto> {
        return api.getStatistics(searchQuery)
    }

    override suspend fun getHistory(country: String): Response<HistoryDto> {
        return api.getHistory(country)
    }
}