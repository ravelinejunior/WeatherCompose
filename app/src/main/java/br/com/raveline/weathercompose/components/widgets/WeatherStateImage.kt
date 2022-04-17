package br.com.raveline.weathercompose.components.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.raveline.weathercompose.R
import coil.compose.rememberImagePainter

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = stringResource(id = R.string.image_string),
        modifier = Modifier.size(80.dp)
    )
}