package com.example.gempaduar.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gempaduar.ui.components.*
import com.example.gempaduar.ui.state.UiState
import com.example.gempaduar.ui.viewmodel.GempaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: GempaViewModel,
    onItemClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var query by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Monitoring Gempa BMKG", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                placeholder = { Text("Cari berdasarkan wilayah...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Cari") },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { query = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Hapus")
                        }
                    }
                },
                singleLine = true
            )

            when (val state = uiState) {
                is UiState.Loading -> LoadingView()
                is UiState.Error -> ErrorView(
                    message = state.message,
                    onRetry = viewModel::loadGempa
                )
                is UiState.Success -> {
                    // Filter lokal berdasarkan wilayah
                    val filtered = remember(state.data, query) {
                        state.data.filter {
                            it.wilayah.orEmpty().contains(query.trim(), ignoreCase = true)
                        }
                    }
                    if (filtered.isEmpty()) {
                        EmptyView("Tidak ada gempa untuk wilayah \"$query\"")
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(filtered, key = { it.id }) { gempa ->
                                GempaItem(gempa = gempa, onClick = { onItemClick(gempa.id) })
                            }
                        }
                    }
                }
            }
        }
    }
}