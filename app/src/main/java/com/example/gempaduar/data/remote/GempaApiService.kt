package com.example.gempaduar.data.remote

import com.example.gempaduar.data.model.GempaResponse
import retrofit2.http.GET

interface GempaApiService {
    @GET("DataMKG/TEWS/gempaterkini.json")
    suspend fun getGempaTerkini(): GempaResponse
}