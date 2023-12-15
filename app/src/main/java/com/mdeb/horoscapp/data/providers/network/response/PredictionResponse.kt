package com.mdeb.horoscapp.data.providers.network.response

import com.google.gson.annotations.SerializedName
import com.mdeb.horoscapp.domain.model.PredictionModel

/*{
    "date": "2020-01-01",
    "horoscope": "Hoy será un día asqueroso para ti.",
    "icon": "https://newastro.vercel.app/static/assets/zodiac-1.png",
    "id": 10,
    "sign": "aries"
}*/

data class PredictionResponse(
    @SerializedName("date") val date:String,
    @SerializedName("horoscope") val horoscope:String,
    @SerializedName("sign") val sign:String,

){
    fun toDomain(): PredictionModel {
        return PredictionModel(horoscope = horoscope,
            sign = sign)
    }
}