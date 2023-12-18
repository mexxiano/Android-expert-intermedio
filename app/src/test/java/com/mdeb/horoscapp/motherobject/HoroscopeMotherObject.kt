package com.mdeb.horoscapp.motherobject

import com.mdeb.horoscapp.data.providers.network.response.PredictionResponse
import com.mdeb.horoscapp.domain.model.HoroscopeInfo

object HoroscopeMotherObject {
    val anyResponse =  PredictionResponse("date", "prediction", "taurus")

    val  horoscopeInfoList = listOf(
    HoroscopeInfo.Aries, HoroscopeInfo.Taurus, HoroscopeInfo.Gemini,
    HoroscopeInfo.Cancer, HoroscopeInfo.Leo, HoroscopeInfo.Virgo,
    HoroscopeInfo.Libra, HoroscopeInfo.Scorpio, HoroscopeInfo.Sagitario,
    HoroscopeInfo.Capricornio, HoroscopeInfo.Aquarius, HoroscopeInfo.Piscis
    )
}