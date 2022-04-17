package br.com.raveline.weathercompose.components.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.raveline.weathercompose.R
import br.com.raveline.weathercompose.data.model.WeatherBody

@Composable
fun HumidityWindPressureRow(weather: WeatherBody) {

    Row(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = stringResource(
                    id = R.string.icon_string_humidity
                ),
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = weather.humidity.toString(),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(2.dp)
            )
        }

        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = stringResource(
                    id = R.string.icon_string_pressure
                ),
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = "${weather.pressure} psi",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(2.dp)
            )
        }

        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = stringResource(
                    id = R.string.icon_string_wind
                ),
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = "${weather.speed} km/h",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(2.dp)
            )
        }

    }

}