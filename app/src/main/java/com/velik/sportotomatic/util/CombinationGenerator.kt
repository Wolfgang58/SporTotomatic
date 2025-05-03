package com.velik.sportotomatic.util

object CombinationGenerator {

    fun generateCombinations(input: List<List<String>>): List<List<String>> {
        val result = mutableListOf<List<String>>()
        generateRecursive(input, 0, mutableListOf(), result)
        return result
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
