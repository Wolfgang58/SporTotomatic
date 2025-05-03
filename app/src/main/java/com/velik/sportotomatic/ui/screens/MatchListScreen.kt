// MatchListScreen.kt
package com.velik.sportotomatic.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.velik.sportotomatic.viewmodel.MainViewModel
import com.velik.sportotomatic.ui.components.MatchItem

@Composable
fun MatchListScreen(viewModel: MainViewModel = viewModel()) {
    val matches by viewModel.matches.collectAsState()
    val selections by viewModel.selections.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f).padding(16.dp)
        ) {
            itemsIndexed(matches) { index, match ->
                MatchItem(
                    match = match,
                    matchIndex = index,
                    selectedResults = selections[index] ?: emptySet(),
                    onResultClick = { viewModel.toggleSelection(index, it) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Button(
            onClick = {
                // Kupon oluşturma ekranına buradan geçilecek
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = selections.size == matches.size && selections.values.all { it.isNotEmpty() }
        ) {
            Text("Kuponu Oluştur")
        }
    }
}
