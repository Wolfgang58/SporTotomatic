package com.velik.sportotomatic.util

object CombinationGenerator {

    fun generateCombinations(selections: List<List<String>>): List<List<String>> {
        if (selections.isEmpty()) return emptyList()

        return selections.fold(listOf(listOf<String>())) { acc, list ->
            acc.flatMap { partial -> list.map { partial + it } }
        }
    }
    private fun generateRecursive(
        input: List<List<String>>,
        index: Int,
        current: MutableList<String>,
        result: MutableList<List<String>>
    ) {
        if (index == input.size) {
            result.add(current.toList())
            return
        }

        for (option in input[index]) {
            current.add(option)
            generateRecursive(input, index + 1, current, result)
            current.removeAt(current.size - 1)
        }
    }
}
