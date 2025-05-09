package com.velik.sportotomatic.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.velik.sportotomatic.domain.model.Match

class MatchRepository(private val context: Context) {

    fun loadMatchesFromAssets(): List<Match> {
        return try {
            val inputStream = context.assets.open("matches.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val matchListType = object : TypeToken<List<Match>>() {}.type
            Gson().fromJson(jsonString, matchListType)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
