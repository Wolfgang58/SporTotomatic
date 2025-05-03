package com.velik.sportotomatic.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.velik.sportotomatic.domain.model.Match
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _matches = MutableStateFlow(generateFakeMatches())
    val matches: StateFlow<List<Match>> = _matches.asStateFlow()

    private val _selections = MutableStateFlow<Map<Int, Set<String>>>(emptyMap())
    val selections: StateFlow<Map<Int, Set<String>>> = _selections

    fun toggleSelection(matchIndex: Int, result: String) {
        val current = _selections.value.toMutableMap()
        val selected = current[matchIndex] ?: emptySet()
        current[matchIndex] = if (selected.contains(result)) {
            selected - result
        } else {
            selected + result
        }
        _selections.value = current
    }

    private fun generateFakeMatches(): List<Match> {
        return (1..15).map {
            Match(
                id=1,
                homeTeam = "Ev Takımı $it",
                awayTeam = "Deplasman Takımı $it",
                date = "2025-05-01",
                probabilities = mapOf("1" to 0.4, "X" to 0.3, "2" to 0.3),
            )
        }
    }
}
