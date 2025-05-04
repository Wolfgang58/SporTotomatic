package com.velik.sportotomatic.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.velik.sportotomatic.viewmodel.MainViewModel

@Composable
fun CouponListScreen(viewModel: MainViewModel = viewModel()) {
    val coupons = viewModel.generatedCoupons.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(
            text = "Oluşturulan Kuponlar",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (coupons.value.isEmpty()) {
            Text("Henüz kupon oluşturulmadı.")
        } else {
            LazyColumn {
                items(coupons.value) { coupon ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            coupon.forEachIndexed { index, result ->
                                Text(text = "Maç ${index + 1}: $result")
                            }
                        }
                    }
                }
            }
        }
    }
}
