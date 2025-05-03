package com.velik.sportotomatic.ui.screens

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.velik.sportotomatic.viewmodel.MainViewModel
import com.velik.sportotomatic.domain.model.Match

@Composable
fun MatchListScreen(viewModel: MainViewModel = viewModel()) {
    val matches by viewModel.matches.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(matches) { match ->
            MatchItem(match)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
@Composable
fun MatchItem(match: Match) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("${match.homeTeam} vs ${match.awayTeam}")
            Text("Tarih: ${match.date}")
            Text(
                text = "İhtimaller: 1: %.1f%% | X: %.1f%% | 2: %.1f%%".format(
                    (match.probabilities["1"] ?: 0.0) * 100,
                    (match.probabilities["X"] ?: 0.0) * 100,
                    (match.probabilities["2"] ?: 0.0) * 100
                )
            )


        }
    }
}
