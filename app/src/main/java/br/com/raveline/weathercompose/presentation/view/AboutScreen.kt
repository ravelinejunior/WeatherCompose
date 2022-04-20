package br.com.raveline.weathercompose.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.raveline.weathercompose.R
import br.com.raveline.weathercompose.components.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = stringResource(id = R.string.about_title_string),
                isMainScreen = false,
                backgroundColor = Color.Transparent,
                icon = Icons.Rounded.ArrowBack,
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = stringResource(id = R.string.about_title_message_string),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier
                        .padding(
                            top = 12.dp,
                            bottom = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = stringResource(id = R.string.about_app_description_string),

                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 48.dp, start = 16.dp, end = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}