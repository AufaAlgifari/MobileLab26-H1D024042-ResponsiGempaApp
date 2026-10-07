package com.example.gempaduar.utils

import com.example.gempaduar.data.model.Gempa

fun String?.orDash(): String = if (this.isNullOrBlank()) "-" else this

fun Gempa.magnitudeValue(): Double = magnitude?.toDoubleOrNull() ?: 0.0