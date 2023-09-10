package com.hasandeniz.covidtracking.domain.model

data class Country(
    val name : String,
    val tests : Int,
    val cases : Int,
    val deaths : Int
)