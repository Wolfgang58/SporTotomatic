package com.velik.sportotomatic.domain.model

data class Match(
    val id: Int,
    val homeTeam: String,
    val awayTeam: String,
    val date: String,
    val probabilities: Map<String, Double>,
    var selectedOptions: List<String> = emptyList()

)
