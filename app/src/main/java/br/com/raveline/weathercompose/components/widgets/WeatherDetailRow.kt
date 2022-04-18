package br.com.raveline.weathercompose.components.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.raveline.weathercompose.data.model.WeatherBody
import br.com.raveline.weathercompose.utils.IMAGE_URL
import br.com.raveline.weathercompose.utils.formatDate
import br.com.raveline.weathercompose.utils.formatDecimals
import java.util.*

@Composable
fun WeatherDetailRow(weather: WeatherBody) {
    val imageUrl = "$IMAGE_URL/${weather.weather.first().icon}.png"

    Surface(
        Modifier
            .padding(vertical = 4.dp,horizontal = 2.dp)
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
            WeatherStateImage(imageUrl = imageUrl, size = 50)
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFF6D00)
            ) {
                Text(
                    text = weather.weather.first().description.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    },
                    modifier = Modifier.padding(horizontal=4.dp, vertical = 2.dp),
                    style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
                )
            }

            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Red.copy(alpha = 0.7f),
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(formatDecimals(weather.temp.max) + "º ")
                }

                withStyle(
                    style = SpanStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(formatDecimals(weather.temp.min) + "º")
                }
            }, modifier = Modifier.padding(horizontal = 4.dp))
        }
    }
}