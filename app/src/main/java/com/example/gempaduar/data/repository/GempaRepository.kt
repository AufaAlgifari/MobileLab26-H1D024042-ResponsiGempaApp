package com.example.gempaduar.data.repository

import com.example.gempaduar.data.model.Gempa
import com.example.gempaduar.data.remote.GempaApiService
import com.example.gempaduar.data.remote.RetrofitInstance

class GempaRepository(
    private val api: GempaApiService = RetrofitInstance.api
) {
    suspend fun getGempaTerkini(): Result<List<Gempa>> = runCatching {
        api.getGempaTerkini()
            .infogempa
            ?.gempa
            .orEmpty()
            .mapIndexed { index, gempa -> gempa.copy(id = index) }
    }
}