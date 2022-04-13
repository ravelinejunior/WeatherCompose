package br.com.raveline.weathercompose.data.repository

import br.com.raveline.weathercompose.data.model.WeatherListModel
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeather(query: String): Response<WeatherListModel>
}