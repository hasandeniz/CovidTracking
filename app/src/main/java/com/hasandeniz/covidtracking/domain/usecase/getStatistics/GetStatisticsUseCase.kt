package com.hasandeniz.covidtracking.domain.usecase.getStatistics

import com.hasandeniz.covidtracking.data.remote.dto.statistics.StatisticDto
import com.hasandeniz.covidtracking.data.util.handleApi
import com.hasandeniz.covidtracking.domain.repository.CovidRepository
import com.hasandeniz.covidtracking.util.Resource
import javax.inject.Inject

class GetStatisticsUseCase @Inject constructor(private val repository: CovidRepository) {
    suspend fun execute(searchQuery: String?): Resource<StatisticDto> {
        return handleApi { repository.getStatistics(searchQuery) }
    }
}