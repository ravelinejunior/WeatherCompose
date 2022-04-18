package br.com.raveline.weathercompose.components.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.raveline.weathercompose.data.model.WeatherBody
import br.com.raveline.weathercompose.utils.IMAGE_URL
import br.com.raveline.weathercompose.utils.formatDate

@Composable
fun WeatherDetailRow(weather: WeatherBody) {
    val imageUrl = "$IMAGE_URL/${weather.weather.first().icon}.png"

    Surface(
        Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(4.dp), bottomStart = CornerSize(4.dp)),
        color = Color.White
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(formatDate(weather.dt).split(",")[0], modifier = Modifier.padding(4.dp))
            WeatherStateImage(imageUrl = imageUrl)
        }
    }
}