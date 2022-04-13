package br.com.raveline.weathercompose.data.model


import com.google.gson.annotations.SerializedName

data class WeatherBody(
    @SerializedName("clouds")
    val clouds: Int = 0,
    @SerializedName("deg")
    val deg: Int = 0,
    @SerializedName("dt")
    val dt: Int = 0,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike = FeelsLike(),
    @SerializedName("gust")
    val gust: Double = 0.0,
    @SerializedName("humidity")
    val humidity: Int = 0,
    @SerializedName("pop")
    val pop: Double = 0.0,
    @SerializedName("pressure")
    val pressure: Double = 0.0,
    @SerializedName("speed")
    val speed: Double = 0.0,
    @SerializedName("sunrise")
    val sunrise: Int = 0,
    @SerializedName("sunset")
    val sunset: Int = 0,
    @SerializedName("temp")
    val temp: Temp = Temp(),
    @SerializedName("weather")
    val weather: List<Weather> = listOf()
)