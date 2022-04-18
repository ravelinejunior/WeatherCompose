package br.com.raveline.weathercompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raveline.weathercompose.data.model.WeatherListModel
import br.com.raveline.weathercompose.data.repository.WeatherRepositoryImpl
import br.com.raveline.weathercompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repositoryImpl: WeatherRepositoryImpl) :
    ViewModel() {

    private val weatherMutableData = MutableStateFlow<Resource<WeatherListModel>>(
        Resource.Loading()
    )

    val weatherData = weatherMutableData.asStateFlow()

    suspend fun getWeatherData(city: String = "Lisbon"): Resource<WeatherListModel> {
        return handleWeatherResponse(repositoryImpl.getWeather(city))
    }

    init {
        loadWeather()
    }

    private fun loadWeather(city: String = "Almada") {
        getWeather(city)
    }

    private fun getWeather(city: String) {
        weatherMutableData.value = Resource.Loading()
        viewModelScope.launch {
            if (city.isEmpty()) return@launch

            val response = repositoryImpl.getWeather(city)

            if (response.isSuccessful) {
                weatherMutableData.value = handleWeatherResponse(response)
            }
        }
    }

    private fun handleWeatherResponse(response: Response<WeatherListModel>): Resource<WeatherListModel> {
        when {
            response.message().toString().contains("timeout") -> {
                return Resource.Error("Timeout")
            }

            response.code() == 402 -> {
                return Resource.Error("API Key Limited!")
            }

            response.body() == null -> {
                return Resource.Error("WeatherBody not found!")
            }

            response.isSuccessful -> {
                val weather = response.body()
                return Resource.Success(weather!!)
            }

            else -> {
                return Resource.Error("General Error.")
            }
        }
    }
}