package com.mdeb.horoscapp.ui.horoscope

import com.mdeb.horoscapp.data.providers.HoroscopeProviders
import com.mdeb.horoscapp.motherobject.HoroscopeMotherObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class HoroscopeViewModelTest{

    @MockK
    lateinit var horoscopeProvider: HoroscopeProviders

    private lateinit var viewModel:HoroscopeViewModel
    @Before
    fun setUp(){
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `when viewmodel is created then horoscopes are loaded`(){

        every {horoscopeProvider.getHoroscopes()}  returns horoscopeInfoList

        viewModel = HoroscopeViewModel(horoscopeProvider)

        val horoscopes = viewModel.horoscope.value

        assertTrue(horoscopes.isNotEmpty())
    }
}