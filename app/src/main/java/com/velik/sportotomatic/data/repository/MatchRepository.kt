package com.velik.sportotomatic.data.repository

import com.velik.sportotomatic.domain.model.Match

class MatchRepository {

    fun getMatches(): List<Match> {
        return List(15) { index ->
            Match(
                id = index,
                homeTeam = "Takım ${index + 1}A",
                awayTeam = "Takım ${index + 1}B",
                date = "2025-05-${10 + index}",
                probabilities = mapOf("1" to 0.4, "X" to 0.3, "2" to 0.3)
            )
        }
    }
}
