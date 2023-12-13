package com.mdeb.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.mdeb.horoscapp.data.providers.HoroscopeProviders
import com.mdeb.horoscapp.domain.model.HoroscopeInfo
import com.mdeb.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(private val horoscopeProvider:HoroscopeProviders): ViewModel() {

    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    init{

        _horoscope.value = horoscopeProvider.getHoroscopes()

       // )
    }
}
