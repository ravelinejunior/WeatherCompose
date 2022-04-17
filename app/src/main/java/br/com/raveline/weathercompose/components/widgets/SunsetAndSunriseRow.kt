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
import br.com.raveline.weathercompose.utils.formatDateTime

@Composable
fun SunsetAndSunriseRow(weather: WeatherBody) {
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
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = stringResource(
                    id = R.string.icon_string_sunrise
                ),
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = formatDateTime(weather.sunrise),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(2.dp)
            )
        }

        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = stringResource(
                    id = R.string.icon_string_sunset
                ),
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = formatDateTime(weather.sunset),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}