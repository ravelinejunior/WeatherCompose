package br.com.raveline.weathercompose.components.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.raveline.weathercompose.R
import br.com.raveline.weathercompose.data.database.entity.FavoriteEntity
import br.com.raveline.weathercompose.data.model.WeatherListModel
import br.com.raveline.weathercompose.presentation.navigation.WeatherScreens
import br.com.raveline.weathercompose.presentation.viewmodel.FavoriteViewModel
import br.com.raveline.weathercompose.utils.aboutString
import br.com.raveline.weathercompose.utils.favoriteString
import br.com.raveline.weathercompose.utils.settingsString


@Composable
fun WeatherAppBar(
    title: String = stringResource(R.string.title_id),
    weather: WeatherListModel? = null,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    backgroundColor: Color = Color.White,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }

    favoriteViewModel.getFavorites(weather)

    TopAppBar(
        modifier = Modifier.padding(8.dp),
        title = {
            Text(
                text = title, color = MaterialTheme.colors.onSecondary, style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 16.sp
                )
            )
        },
        backgroundColor = backgroundColor,
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    onAddActionClicked.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon)
                    )
                }
                IconButton(onClick = {
                    showDialog.value = true
                }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = stringResource(R.string.more_icon)
                    )
                }

            } else {
                Box {}
            }
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    })
            }
            if (isMainScreen) {

                Icon(
                    imageVector = if (favoriteViewModel.isFavorite.value) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                    contentDescription = stringResource(
                        id = R.string.icon_general_string,
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 4.dp)
                        .scale(0.9f)
                        .clickable {

                            if (weather != null) {
                                favoriteViewModel.insertFavorite(
                                    FavoriteEntity(
                                        id = 0,
                                        cityName = weather.city.name,
                                        icon = weather.list.first().weather.first().icon,
                                        description = weather.list.first().weather.first().description,
                                        cityId = weather.city.id,
                                        countryName = weather.city.country,
                                        population = weather.city.population
                                    )
                                )
                            } else {
                                Log.e("favoriteViewModel", "Something went wrong!")
                            }

                        },
                    tint = Color.Red.copy(alpha = 0.6f)
                )
            }
        },
        elevation = elevation,
    )
}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var isExpanded by remember {
        mutableStateOf(true)
    }

    val dropItems = listOf(
        aboutString, favoriteString, settingsString
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 48.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = isExpanded, onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .background(Color.White)
        ) {
            dropItems.forEach { item ->
                DropdownMenuItem(onClick = {
                    isExpanded = true
                    showDialog.value = false
                }) {
                    Icon(
                        imageVector = when (item) {
                            aboutString -> Icons.Default.Info
                            favoriteString -> Icons.Default.Favorite

                            else -> Icons.Rounded.Settings
                        },

                        contentDescription = stringResource(id = R.string.icon_general_string),
                        tint = when (item) {
                            aboutString -> Color.Gray
                            favoriteString -> Color.Red

                            else -> Color.DarkGray
                        },
                    )

                    Text(
                        text = item,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(
                                    when (item) {
                                        aboutString -> WeatherScreens.AboutScreen.name
                                        favoriteString -> WeatherScreens.FavoriteScreen.name

                                        else -> WeatherScreens.SettingsScreen.name
                                    },
                                )
                            }
                            .padding(horizontal = 16.dp),
                        fontWeight = FontWeight.W700,
                    )

                }
            }
        }
    }
}












