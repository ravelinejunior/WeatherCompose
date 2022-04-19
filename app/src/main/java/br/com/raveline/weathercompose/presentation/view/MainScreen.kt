package br.com.raveline.weathercompose.presentation.view

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.raveline.weathercompose.R
import br.com.raveline.weathercompose.components.widgets.*
import br.com.raveline.weathercompose.data.model.WeatherListModel
import br.com.raveline.weathercompose.presentation.navigation.WeatherScreens
import br.com.raveline.weathercompose.presentation.viewmodel.WeatherViewModel
import br.com.raveline.weathercompose.utils.IMAGE_URL
import br.com.raveline.weathercompose.utils.formatDate
import br.com.raveline.weathercompose.utils.formatDecimals
import kotlinx.coroutines.launch
import retrofit2.Response

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    navController: NavController, viewModel: WeatherViewModel, city: String?
) {
    val scope = rememberCoroutineScope()


    val weatherState = produceState<Response<WeatherListModel>>(Response.success(null)) {
        value = viewModel.getWeatherResponse(city.toString())
    }

    if (weatherState.value.isSuccessful) {
        val weather = weatherState.value.body()
        if (weather != null) {
            MainScaffold(weather = weather, navController = navController)
        } else {
            CircularProgressIndicator()
        }
    } else if (weatherState.value.code() == 404 || weatherState.value.code() != 200) {
        val _weatherState = produceState<Response<WeatherListModel>>(Response.success(null)) {
            value = viewModel.getWeatherResponse("Lisbon")
        }

        if (_weatherState.value.isSuccessful) {
            val weather = _weatherState.value.body()
            if (weather != null) {
                MainScaffold(weather = weather, navController = navController)
            } else {
                CircularProgressIndicator()
            }
        }


    } else {
        CircularProgressIndicator(
            progress = 100f
        )
    }

}

@Composable
fun MainScaffold(weather: WeatherListModel, navController: NavController) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = "${weather.city.name},${weather.city.country}",
            navController = navController,
            elevation = 6.dp,
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            }
        )
    }) {
        MainContent(data = weather)

    }


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
                .size(180.dp),
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
                    style = MaterialTheme.typography.h5,
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
            modifier = Modifier.padding(4.dp)
        )

        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            color = Color(0xF7F1F1F1),
            shape = RoundedCornerShape(size = 16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = data.list) { weather ->

                    WeatherDetailRow(weather = weather)

                }
            }
        }

    }


}




















