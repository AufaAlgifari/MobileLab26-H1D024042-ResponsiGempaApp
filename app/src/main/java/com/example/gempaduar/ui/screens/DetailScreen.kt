package com.example.gempaduar.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gempaduar.ui.components.DetailRow
import com.example.gempaduar.ui.components.EmptyView
import com.example.gempaduar.ui.components.MagnitudeBadge
import com.example.gempaduar.ui.viewmodel.GempaViewModel
import com.example.gempaduar.utils.magnitudeValue
import com.example.gempaduar.utils.orDash

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    gempaId: Int,
    viewModel: GempaViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val gempa = remember(uiState, gempaId) { viewModel.getGempaById(gempaId) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Gempa", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        if (gempa == null) {
            EmptyView("Data gempa tidak ditemukan", Modifier.padding(padding))
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                MagnitudeBadge(gempa.magnitude, gempa.magnitudeValue())
                Spacer(Modifier.height(16.dp))
                DetailRow("TANGGAL", gempa.tanggal.orDash())
                DetailRow("JAM", gempa.jam.orDash())
                DetailRow("COORDINATES", gempa.coordinates.orDash())
                DetailRow("MAGNITUDO", gempa.magnitude.orDash())
                DetailRow("KEDALAMAN", gempa.kedalaman.orDash())
                DetailRow("WILAYAH", gempa.wilayah.orDash())
                DetailRow("POTENSI", gempa.potensi.orDash())
            }
        }
    }
}