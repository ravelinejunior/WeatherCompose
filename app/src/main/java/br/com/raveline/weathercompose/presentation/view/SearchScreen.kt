package br.com.raveline.weathercompose.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.raveline.weathercompose.R
import br.com.raveline.weathercompose.components.widgets.CommonTextField
import br.com.raveline.weathercompose.components.widgets.WeatherAppBar
import br.com.raveline.weathercompose.presentation.navigation.WeatherScreens

@Composable
fun SearchScreen(
    navController: NavController
) {
    Scaffold(topBar = {
        WeatherAppBar(
            navController = navController,
            icon = Icons.Rounded.ArrowBack,
            isMainScreen = false,
            title = stringResource(
                id = R.string.search_title_string
            )
        ) {
            navController.popBackStack()
        }
    }) {
        Surface() {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(){}
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    onSearch: (String) -> Unit = {}
) {

    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotBlank()
    }

    Column {
        CommonTextField(
            valueState = searchQueryState,
            placeHolder = stringResource(id = R.string.place_holder_new_york_string),
            onAction = KeyboardActions { }
        )
    }
}


