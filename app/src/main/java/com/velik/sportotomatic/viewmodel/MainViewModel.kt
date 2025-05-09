package com.velik.sportotomatic.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.velik.sportotomatic.data.MatchRepository
import com.velik.sportotomatic.domain.model.Match
import com.velik.sportotomatic.util.CombinationGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MatchRepository(application)

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> = _matches

    private val _userSelections = MutableStateFlow<Map<Int, List<String>>>(emptyMap())
    val userSelections: StateFlow<Map<Int, List<String>>> = _userSelections

    private val _generatedCoupons = MutableStateFlow<List<List<String>>>(emptyList())
    val generatedCoupons: StateFlow<List<List<String>>> = _generatedCoupons

    init {
        _matches.value = repository.loadMatchesFromAssets()
    }
    fun calculateTotalPrice(): Int {
        val selectionCounts = _userSelections.value.values.map { it.size }
        return when {
            selectionCounts.all { it == 1 } -> 10
            selectionCounts.count { it == 2 } == 1 && selectionCounts.count { it == 1 } == 14 -> 20
            selectionCounts.count { it == 3 } == 1 && selectionCounts.count { it == 1 } == 14 -> 30
            else -> {
                val totalCombinations = selectionCounts.fold(1) { acc, size -> acc * size }
                10 * totalCombinations
            }
        }
    }


    fun updateUserSelection(matchId: Int, selections: List<String>) {
        val updated = _userSelections.value.toMutableMap()
        updated[matchId] = selections
        _userSelections.value = updated
    }

    fun generateCoupons() {
        val sortedSelections = _matches.value.map { match ->
            _userSelections.value[match.id] ?: listOf("1", "X", "2")
        }

        val combinations = CombinationGenerator.generateCombinations(sortedSelections)
        _generatedCoupons.value = combinations
    }

}
