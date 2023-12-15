package com.mdeb.horoscapp.data.providers

import android.util.Log
import com.mdeb.horoscapp.data.providers.network.HoroscopeApiService
import com.mdeb.horoscapp.domain.model.PredictionModel
import com.mdeb.horoscapp.domain.model.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService:HoroscopeApiService) : Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        // PEticion retrofit
      kotlin.runCatching { apiService.getHoroscope(sign) }
           .onSuccess { return it.toDomain() }
          .onFailure { Log.i("aris", "ha ocurrido un  error ${it.message}") }

        return null
    }
}
