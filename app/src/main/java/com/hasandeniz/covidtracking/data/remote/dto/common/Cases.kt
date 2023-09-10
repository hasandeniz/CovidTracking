package com.hasandeniz.covidtracking.data.remote.dto.common

data class Cases(
    val active: Int,
    val critical: Int,
    val new: String,
    val recovered: Int,
    val total: Int
)