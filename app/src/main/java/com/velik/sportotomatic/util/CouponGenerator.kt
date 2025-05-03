package com.velik.sportotomatic.domain.util

import com.velik.sportotomatic.domain.model.Match

object CouponGenerator {

    fun generateCoupons(matches: List<Match>): List<List<String>> {
        val allChoices = matches.map { match ->
            match.probabilities.keys.toList() // ["1", "X", "2"] gibi
        }

        return combine(allChoices)
    }

    private fun combine(lists: List<List<String>>): List<List<String>> {
        return lists.fold(listOf(listOf())) { acc, list ->
            acc.flatMap { combination -> list.map { choice -> combination + choice } }
        }
    }
}
