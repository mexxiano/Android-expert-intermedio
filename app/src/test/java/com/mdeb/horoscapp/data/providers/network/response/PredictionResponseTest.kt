package com.mdeb.horoscapp.data.providers.network.response

import com.mdeb.horoscapp.motherobject.HoroscopeMotherObject
import com.mdeb.horoscapp.motherobject.HoroscopeMotherObject.anyResponse
import io.kotlintest.shouldBe
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class PredictionResponseTest{
    @Test
    fun `toDomain should return a correct PredictionModel`() {
        // Given: te doy la info necesaria para iniciar
        val horoscopeResponse = anyResponse   //PredictionResponse("date", "prediction", "taurus")

        // When: cuando pase o se ejecute
        val predictionModel = horoscopeResponse.toDomain()

        // Then: entonces verifico que ha pasado algo
        predictionModel.sign shouldBe horoscopeResponse.sign
        predictionModel.horoscope shouldBe  horoscopeResponse.horoscope

    }
}