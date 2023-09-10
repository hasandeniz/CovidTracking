package com.hasandeniz.covidtracking.domain.usecase.getHistory

import com.hasandeniz.covidtracking.data.remote.dto.history.HistoryDto
import com.hasandeniz.covidtracking.data.util.handleApi
import com.hasandeniz.covidtracking.domain.repository.CovidRepository
import com.hasandeniz.covidtracking.util.Resource
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(private val repository: CovidRepository) {
    suspend fun execute(country: String): Resource<HistoryDto> {
        return handleApi { repository.getHistory(country) }
    }
}