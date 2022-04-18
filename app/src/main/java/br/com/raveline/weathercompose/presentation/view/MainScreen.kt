package br.com.raveline.weathercompose.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.raveline.weathercompose.R
import br.com.raveline.weathercompose.components.widgets.*
import br.com.raveline.weathercompose.data.model.WeatherListModel
import br.com.raveline.weathercompose.presentation.viewmodel.WeatherViewModel
import br.com.raveline.weathercompose.utils.IMAGE_URL
import br.com.raveline.weathercompose.utils.Resource
import br.com.raveline.weathercompose.utils.formatDate
import br.com.raveline.weathercompose.utils.formatDecimals

@Composable
fun MainScreen(
    navController: NavController, viewModel: WeatherViewModel
) {

    val weatherListModel = viewModel.weatherData.collectAsState().value
    when (weatherListModel.data) {
        Resource.Loading(null).data -> {
            CircularProgressIndicator()
        }
        else -> {
            MainScaffold(weather = weatherListModel.data, navController = navController)
        }
    }

}

@Composable
fun MainScaffold(weather: WeatherListModel, navController: NavController) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = "${weather.city.name},${weather.city.country}",
            navController = navController,
            elevation = 6.dp
        )
    }) {
        MainContent(data = weather)

    }


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


}

@Composable
fun MainContent(data: WeatherListModel) {

    val imageUrl = "$IMAGE_URL/${data.list.first().weather.first().icon}.png"

    Column(
        Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(data.list.first().dt),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(8.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFAB00)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = "${formatDecimals(data.list.first().temp.day)}º",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = data.list.first().weather.first().main,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        HumidityWindPressureRow(weather = data.list.first())

        Divider(modifier = Modifier.padding(horizontal = 8.dp))

        SunsetAndSunriseRow(weather = data.list.first())

        Text(
            text = stringResource(id = R.string.this_week_string),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(16.dp)
        )

        Surface(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
            color = Color(0xF7D5D5D5),
            shape = RoundedCornerShape(size = 16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(2.dp),
                    contentPadding = PaddingValues(4.dp)
                ){
                    items(items = data.list){ weather ->

                        WeatherDetailRow(weather = weather)

                    }
                }
        }

    }


}




















