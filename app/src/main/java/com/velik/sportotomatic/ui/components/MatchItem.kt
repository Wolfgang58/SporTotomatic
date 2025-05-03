package com.velik.sportotomatic.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velik.sportotomatic.domain.model.Match

@Composable
fun MatchItem(match: Match) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = "${match.homeTeam} vs ${match.awayTeam}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Tarih: ${match.date}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "İhtimaller: 1: ${(match.probabilities["1"] ?: 0.0) * 100}%, " +
                        "X: ${(match.probabilities["X"] ?: 0.0) * 100}%, " +
                        "2: ${(match.probabilities["2"] ?: 0.0) * 100}%"
            )

        }
    }
}
