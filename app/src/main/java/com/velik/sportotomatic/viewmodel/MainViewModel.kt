package com.velik.sportotomatic.viewmodel

import androidx.lifecycle.ViewModel
import com.velik.sportotomatic.domain.model.Match
import com.velik.sportotomatic.util.CombinationGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MainViewModel : ViewModel() {

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> = _matches

    private val _userSelections = MutableStateFlow<Map<Int, List<String>>>(emptyMap())
    val userSelections: StateFlow<Map<Int, List<String>>> = _userSelections

    private val _generatedCoupons = MutableStateFlow<List<List<String>>>(emptyList())
    val generatedCoupons: StateFlow<List<List<String>>> = _generatedCoupons

    private val _totalPrice = MutableStateFlow(0)
    val totalPrice: StateFlow<Int> = _totalPrice

    init {
        _matches.value = List(15) { index ->
            Match(
                id = index,
                homeTeam = "Takım ${index + 1}A",
                awayTeam = "Takım ${index + 1}B",
                date = "2025-05-${10 + index}",
                probabilities = mapOf("1" to 0.4, "X" to 0.3, "2" to 0.3)
            )
        }
    }

    fun updateUserSelection(matchId: Int, selections: List<String>) {
        val updated = _userSelections.value.toMutableMap()
        updated[matchId] = selections
        _userSelections.value = updated
    }

    fun generateCoupons() {
        val selections = _matches.value.map { match ->
            _userSelections.value[match.id]?.ifEmpty { listOf("1", "X", "2") }
                ?: listOf("1", "X", "2")
        }

        val combinations = CombinationGenerator.generateCombinations(selections)
        _generatedCoupons.value = combinations

        val total = combinations.size * 10 // 1 kupon = 10 TL
        _totalPrice.value = total
    }
}
