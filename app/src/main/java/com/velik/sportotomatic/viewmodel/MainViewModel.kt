package com.velik.sportotomatic.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.velik.sportotomatic.domain.model.Match
import com.velik.sportotomatic.util.CombinationGenerator

class MainViewModel : ViewModel() {

    // Maç listesini tutar
    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> = _matches

    // Kullanıcının maçlara yaptığı seçimler (ör: {0: ["1", "X"], 1: ["2"]})
    private val _userSelections = MutableStateFlow<Map<Int, List<String>>>(emptyMap())
    val userSelections: StateFlow<Map<Int, List<String>>> = _userSelections

    // Oluşturulan kuponlar burada tutulur
    private val _generatedCoupons = MutableStateFlow<List<List<String>>>(emptyList())
    val generatedCoupons: StateFlow<List<List<String>>> = _generatedCoupons

    init {
        // Demo verisi
        _matches.value = generateFakeMatches()
    }

    // Kullanıcının seçimini günceller
    fun updateUserSelection(matchId: Int, selections: List<String>) {
        val updated = _userSelections.value.toMutableMap()
        updated[matchId] = selections
        _userSelections.value = updated
    }

    // Kuponları oluşturur (seçimlerden tüm olası kombinasyonları üretir)
    fun generateCoupons() {
        val sortedSelections = _matches.value.map { match ->
            _userSelections.value[match.id] ?: listOf("1", "X", "2") // seçim yapılmadıysa hepsi
        }

        val combinations = CombinationGenerator.generateCombinations(sortedSelections)
        _generatedCoupons.value = combinations
    }

    private fun generateFakeMatches(): List<Match> {
        return List(15) { index ->
            Match(
                id = index,
                homeTeam = "Takım ${index + 1}A",
                awayTeam = "Takım ${index + 1}B",
                date = "2025-05-${10 + index}",
                probabilities = mapOf("1" to 0.4, "X" to 0.3, "2" to 0.3),
                result = "" // ← EKLENEN KISIM
            )
        }
    }

}
