package com.velik.sportotomatic.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Maç Tarihi
            Text(
                text = match.date,
                fontSize = 14.sp,
                color = Color(0xFF333333),
                modifier = Modifier.width(70.dp)
            )

            // Takım İsimleri
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${match.homeTeam} vs ${match.awayTeam}",
                    fontSize = 16.sp,
                    color = Color(0xFF333333)
                )
            }

            // Seçenekler: 1 X 2
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf("1", "X", "2").forEach { result ->
                    val isSelected = selectedResults.contains(result)
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                if (isSelected) Color(0xFF00A651) else Color(0xFFD3D3D3),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                val updatedSelections = if (isSelected) {
                                    selectedResults - result
                                } else {
                                    selectedResults + result
                                }
                                onSelectionChange(updatedSelections)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = result,
                            color = if (isSelected) Color.White else Color.Black,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
