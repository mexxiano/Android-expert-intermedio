package com.mdeb.horoscapp.domain.model

import com.mdeb.horoscapp.data.providers.network.response.PredictionResponse

interface Repository {
    suspend fun getPrediction(sing:String): PredictionModel?
}