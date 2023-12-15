package com.mdeb.horoscapp.data.providers.network

import com.mdeb.horoscapp.data.providers.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiService {

    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign:String ): PredictionResponse
}