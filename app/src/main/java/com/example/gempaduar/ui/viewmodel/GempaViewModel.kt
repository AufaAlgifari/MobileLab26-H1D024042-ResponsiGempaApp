package com.example.gempaduar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gempaduar.data.model.Gempa
import com.example.gempaduar.data.repository.GempaRepository
import com.example.gempaduar.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GempaViewModel(
    private val repository: GempaRepository = GempaRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadGempa()
    }

    fun loadGempa() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getGempaTerkini()
                .onSuccess { _uiState.value = UiState.Success(it) }
                .onFailure {
                    _uiState.value = UiState.Error(
                        it.localizedMessage ?: "Terjadi kesalahan. Periksa koneksi internet."
                    )
                }
        }
    }

    fun getGempaById(id: Int): Gempa? =
        (uiState.value as? UiState.Success)?.data?.firstOrNull { it.id == id }
}