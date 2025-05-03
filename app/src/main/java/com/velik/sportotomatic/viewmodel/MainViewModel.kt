package com.velik.sportotomatic.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.velik.sportotomatic.domain.model.Match

class MainViewModel : ViewModel() {

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> = _matches

    init {
        _matches.value = generateDummyMatches()
    }

    private fun generateDummyMatches(): List<Match> {
        val dummyList = mutableListOf<Match>()
        for (i in 1..15) {
            val probs: Map<String, Double> = mapOf(
                "1" to 0.4,
                "X" to 0.3,
                "2" to 0.3
            )
            dummyList.add(
                Match(
                    id = i,
                    homeTeam = "Ev Takımı $i",
                    awayTeam = "Deplasman Takımı $i",
                    date = "2025-05-01",
                    probabilities = probs
                )
            )
        }
        return dummyList
    }
}
