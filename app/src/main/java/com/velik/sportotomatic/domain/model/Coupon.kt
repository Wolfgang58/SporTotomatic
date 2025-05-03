package com.velik.sportotomatic.domain.model

data class Coupon(
    val combinations: List<List<String>>, // Her maç için seçilen tahmin ["1", "X", "2"] gibi
    val totalCombinations: Int
)
