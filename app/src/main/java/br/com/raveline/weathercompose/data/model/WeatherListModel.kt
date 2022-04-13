package br.com.raveline.weathercompose.data.model


import com.google.gson.annotations.SerializedName

data class WeatherListModel(
    @SerializedName("city")
    val city: City = City(),
    @SerializedName("cnt")
    val cnt: Int = 0,
    @SerializedName("cod")
    val cod: String = "",
    @SerializedName("list")
    val list: List<WeatherBody> = listOf(),
    @SerializedName("message")
    val message: Double = 0.0
)