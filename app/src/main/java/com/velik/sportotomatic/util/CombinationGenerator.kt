package com.velik.sportotomatic.util

object CombinationGenerator {
    fun generateCombinations(options: List<List<String>>): List<List<String>> {
        if (options.isEmpty()) return emptyList()
        return options.fold(listOf(listOf())) { acc, list ->
            acc.flatMap { prefix -> list.map { choice -> prefix + choice } }
        }
    }
}
