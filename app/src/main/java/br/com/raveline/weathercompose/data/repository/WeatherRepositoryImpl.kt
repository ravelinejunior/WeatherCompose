package br.com.raveline.weathercompose.data.repository

import br.com.raveline.weathercompose.data.model.WeatherListModel
import br.com.raveline.weathercompose.data.network.services.WeatherApi
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getWeather(query: String): Response<WeatherListModel> {
        return api.getCurrentWeather(query)
    }
}

