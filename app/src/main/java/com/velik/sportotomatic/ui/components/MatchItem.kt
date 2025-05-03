// MatchItem.kt
package com.velik.sportotomatic.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.velik.sportotomatic.domain.model.Match

@Composable
fun MatchItem(
    match: Match,
    matchIndex: Int,
    selectedResults: Set<String>,
    onResultClick: (String) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("${match.homeTeam} vs ${match.awayTeam}")
            Text("Tarih: ${match.date}")
            Text(
                "İhtimaller: 1: ${(match.probabilities["1"] ?: 0.0) * 100}%, " +
                        "X: ${(match.probabilities["X"] ?: 0.0) * 100}%, " +
                        "2: ${(match.probabilities["2"] ?: 0.0) * 100}%"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                listOf("1", "X", "2").forEach { result ->
                    val isSelected = result in selectedResults
                    Button(
                        onClick = { onResultClick(result) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Color(0xFF4D5B9E) else Color.LightGray,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .weight(1f)
                    ) {
                        Text(result)
                    }
                }
            }
        }
    }
}
