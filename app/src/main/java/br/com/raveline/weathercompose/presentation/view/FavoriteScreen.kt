package br.com.raveline.weathercompose.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.raveline.weathercompose.R
import br.com.raveline.weathercompose.components.widgets.WeatherAppBar
import br.com.raveline.weathercompose.components.widgets.WeatherStateImage
import br.com.raveline.weathercompose.data.database.entity.FavoriteEntity
import br.com.raveline.weathercompose.presentation.navigation.WeatherScreens
import br.com.raveline.weathercompose.presentation.viewmodel.FavoriteViewModel
import br.com.raveline.weathercompose.utils.IMAGE_URL
import java.util.*

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = stringResource(id = R.string.favorite_title_bar_string),
                icon = Icons.Rounded.ArrowBack,
                isMainScreen = false,
            ) {
                navController.popBackStack()
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()

        ) {
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val favorites = favoriteViewModel.favList.collectAsState().value

                    LazyColumn(modifier = Modifier.padding(vertical = 16.dp)) {
                        items(items = favorites) {
                            CityRow(it, navController = navController, favoriteViewModel)
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun CityRow(
    favorite: FavoriteEntity,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name + "/${favorite.cityName}")
            },
        shape = CircleShape.copy(topStart = CornerSize(8.dp), bottomEnd = CornerSize(8.dp)),
        elevation = 16.dp
    ) {
        val imageUrl = "$IMAGE_URL/${favorite.icon}.png"

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0D47A1),
                            Color(0xFF1976D2)
                        )
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "${favorite.cityName}, ${favorite.countryName}",
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                )

                Text(
                    text = "Population: ${favorite.population}",
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )

            }

            Text(
                text = favorite.description.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.White
                )
            )

            Surface(
                modifier = Modifier
                    .padding(4.dp)
                    .size(30.dp), shape = CircleShape
            ) {
                WeatherStateImage(imageUrl = imageUrl)
            }

            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = stringResource(id = R.string.icon_general_string),
                tint = Color.Red,
                modifier = Modifier
                    .clickable {
                        favoriteViewModel.deleteFavorite(favorite)
                    }
                    .size(20.dp)
            )

        }
    }

}















