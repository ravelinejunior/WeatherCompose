package br.com.raveline.weathercompose.data.network.services

import br.com.raveline.weathercompose.data.model.WeatherListModel
import br.com.raveline.weathercompose.utils.GEN_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET("forecast/daily")
    suspend fun getCurrentWeather(
        @Query("q") query: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = GEN_API_KEY,
        @Query("lang") language:String = "pt_br"

        ): Response<WeatherListModel>
}