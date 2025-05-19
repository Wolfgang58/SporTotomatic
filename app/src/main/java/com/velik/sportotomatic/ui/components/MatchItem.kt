package com.velik.sportotomatic.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.velik.sportotomatic.domain.model.Match

@Composable
fun MatchItem(
    match: Match,
    selectedResults: List<String>,
    onSelectionChange: (List<String>) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("${match.homeTeam} vs ${match.awayTeam} (${match.date})")

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("1", "X", "2").forEach { option ->
                    val isSelected = selectedResults.contains(option)
                    Button(
                        onClick = {
                            val updated = if (isSelected) {
                                selectedResults - option
                            } else {
                                selectedResults + option
                            }
                            onSelectionChange(updated)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Color.Green else Color.LightGray
                        )
                    ) {
                        Text(option)
                    }
                }
            }
        }
    }
}


