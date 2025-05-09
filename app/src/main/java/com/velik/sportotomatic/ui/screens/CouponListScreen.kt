package com.velik.sportotomatic.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.velik.sportotomatic.viewmodel.MainViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CouponListScreen(
    viewModel: MainViewModel = viewModel(),
    onBack: () -> Unit
) {
    val coupons = viewModel.generatedCoupons.collectAsState().value
    val price = viewModel.totalPrice.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Oluşturulan Kuponlar") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Geri")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Toplam Kupon: ${coupons.size}  •  Tutar: ${price} TL",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn {
                items(coupons) { combo ->
                    Text(text = combo.joinToString(" - "))
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}
