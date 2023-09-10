package com.hasandeniz.covidtracking.data.remote.dto

import com.hasandeniz.covidtracking.data.remote.dto.history.HistoryDto
import com.hasandeniz.covidtracking.data.remote.dto.statistics.StatisticDto
import com.hasandeniz.covidtracking.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CovidApi {

    @GET("/statistics")
    suspend fun getStatistics(
        @Query("country") country: String?,
        @Header("X-RapidAPI-Host") host: String? = "covid-193.p.rapidapi.com\"",
        @Header("X-RapidAPI-Key") api: String = API_KEY
    ): Response<StatisticDto>

    @GET("/history")
    suspend fun getHistory(
        @Query("country") country: String?,
        @Header("X-RapidAPI-Host") host: String? = "covid-193.p.rapidapi.com\"",
        @Header("X-RapidAPI-Key") api: String = API_KEY
    ): Response<HistoryDto>
}