package com.velik.sportotomatic.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.velik.sportotomatic.ui.components.MatchItem
import com.velik.sportotomatic.viewmodel.MainViewModel

@Composable
fun MatchListScreen(viewModel: MainViewModel = viewModel()) {
    val matches by viewModel.matches.collectAsState()
    val userSelections by viewModel.userSelections.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(matches) { match ->
                MatchItem(
                    match = match,
                    selectedResults = userSelections[match.id] ?: emptyList(),
                    onSelectionChange = { updatedList ->
                        viewModel.updateUserSelection(match.id, updatedList)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

        }

        Button(
            onClick = { viewModel.generateCoupons() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kuponları Oluştur")
        }
    }
}
