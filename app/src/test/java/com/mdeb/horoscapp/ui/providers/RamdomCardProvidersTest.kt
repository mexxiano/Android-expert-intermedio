package com.mdeb.horoscapp.ui.providers

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class RamdomCardProvidersTest{

    @Test
    fun `getRandomCard should return a random card`(){
        //GIVEN
        val randomCard = RamdomCardProviders()

        //THEN
        val card = randomCard.getLucky()

        //WHEN
        assertNotNull(card)
    }
}