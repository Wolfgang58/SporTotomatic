package com.velik.sportotomatic.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.velik.sportotomatic.domain.model.Match

@Composable
fun MatchItem(
    match: Match,
    onSelectionChange: (List<String>) -> Unit
) {
    var selectedOptions by remember { mutableStateOf<List<String>>(emptyList()) }

    fun toggleSelection(option: String) {
        selectedOptions = if (selectedOptions.contains(option)) {
            selectedOptions - option
        } else {
            selectedOptions + option
        }
        onSelectionChange(selectedOptions)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E2F)) // Lacivertimsi koyu renk
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${match.homeTeam} vs ${match.awayTeam}",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Tarih: ${match.date}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.LightGray
            )
            Text(
                text = "1: ${match.probabilities["1"] ?: 0.0 * 100}%, X: ${match.probabilities["X"] ?: 0.0 * 100}%, 2: ${match.probabilities["2"] ?: 0.0 * 100}%",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("1", "X", "2").forEach { option ->
                    val isSelected = selectedOptions.contains(option)
                    Box(
                        modifier = Modifier
                            .background(
                                if (isSelected) Color(0xFF2196F3) else Color.Transparent,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isSelected) Color(0xFF2196F3) else Color.Gray,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .clickable { toggleSelection(option) }
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = option,
                            color = if (isSelected) Color.White else Color.Gray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
