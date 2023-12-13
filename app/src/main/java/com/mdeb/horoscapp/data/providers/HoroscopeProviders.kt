package com.mdeb.horoscapp.data.providers

import com.mdeb.horoscapp.domain.model.HoroscopeInfo
import javax.inject.Inject

class HoroscopeProviders @Inject constructor() {
    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            HoroscopeInfo.Aries, HoroscopeInfo.Taurus, HoroscopeInfo.Gemini,
            HoroscopeInfo.Cancer, HoroscopeInfo.Leo, HoroscopeInfo.Virgo,
            HoroscopeInfo.Libra, HoroscopeInfo.Scorpio, HoroscopeInfo.Sagitario,
            HoroscopeInfo.Capricornio, HoroscopeInfo.Aquarius, HoroscopeInfo.Piscis
        )
    }
}