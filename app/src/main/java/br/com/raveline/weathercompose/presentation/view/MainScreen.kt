package br.com.raveline.weathercompose.presentation.view

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.lifecycle.liveData
import androidx.navigation.NavController
import br.com.raveline.weathercompose.data.model.WeatherListModel
import br.com.raveline.weathercompose.presentation.viewmodel.WeatherViewModel
import br.com.raveline.weathercompose.utils.Resource

@Composable
fun MainScreen(
    navController: NavController, viewModel: WeatherViewModel

) {
    ShowData(viewModel = viewModel)
}

@Composable
fun ShowData(viewModel: WeatherViewModel) {

  /*  val weatherDataState = produceState<Resource<WeatherListModel>>(initialValue = Resource.Loading()){
        value = viewModel.getWeatherData("Lisbon")
    }

    if (weatherDataState.value.data == Resource.Loading<WeatherListModel>().data){
        CircularProgressIndicator()
    }else if(weatherDataState.value.data != null){
        val weather = weatherDataState.value.data
        Text(text = weather?.list.toString())
    }else{
        CircularProgressIndicator(
            progress = 100f
        )
    }
*/
    val weatherListModel = viewModel.weatherData.collectAsState().value

    when (weatherListModel.data) {
        Resource.Loading(null).data -> {
            CircularProgressIndicator()
        }
        else -> {
            Text(text = weatherListModel.data.toString())
        }
    }

}