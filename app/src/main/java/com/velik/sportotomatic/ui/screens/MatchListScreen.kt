package com.velik.sportotomatic.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.velik.sportotomatic.viewmodel.MainViewModel
import com.velik.sportotomatic.ui.components.MatchItem

@Composable
fun MatchListScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    val matches by viewModel.matches.collectAsState()
    val userSelections by viewModel.userSelections.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(matches) { match ->
                val selected = userSelections[match.id] ?: emptyList()
                MatchItem(
                    match = match,
                    selectedResults = selected,
                    onSelectionChange = { updated ->
                        viewModel.updateUserSelection(match.id, updated)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Button(
            onClick = {
                viewModel.generateCoupons()
                navController.navigate("couponList")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Kuponları Oluştur")
        }
    }
}
