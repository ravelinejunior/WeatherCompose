package br.com.raveline.weathercompose.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raveline.weathercompose.data.database.entity.FavoriteEntity
import br.com.raveline.weathercompose.data.model.WeatherListModel
import br.com.raveline.weathercompose.data.repository.implementations.FavoriteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepositoryImpl) :
    ViewModel() {

    private val mutableFavList = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favList get() = mutableFavList.asStateFlow()

    private val mutableIsFavorite = MutableStateFlow(false)
    val isFavorite get() = mutableIsFavorite.asStateFlow()

    init {
        getFavorites()
    }

     fun getFavorites(weatherListModel: WeatherListModel?=null) {
        viewModelScope.launch {
            withContext(IO) {
                favoriteRepository.getAllFavoritePlaces().distinctUntilChanged()
                    .collectLatest { favorites ->
                        if (favorites.isNotEmpty()) {
                            mutableFavList.value = favorites
                            val findElement = favList.value.find { favorite ->
                                favorite.cityId == weatherListModel?.city?.id
                            }

                            if (findElement != null){
                                mutableIsFavorite.value = true
                            }

                        } else {
                            Log.i("getFavorites", "Favorite list empty!")
                        }
                    }
            }
        }
    }

    fun insertFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch(IO) {

        if (favList.value.isEmpty()) {
            favoriteRepository.insertFavoritePlace(favoriteEntity)
        } else {

            val findElement = favList.value.find { favorite ->
                favorite.cityId == favoriteEntity.cityId
            }

            if (findElement == null) {
                favoriteRepository.insertFavoritePlace(favoriteEntity)
                mutableIsFavorite.value = true
            } else {
                Log.i("getFavorites", "Place already is on database!")
            }
        }

    }

    fun updateFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        favoriteRepository.updateFavoritePlace(favoriteEntity = favoriteEntity)
    }

    fun deleteFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        favoriteRepository.deleteFavoritePlace(favoriteEntity)
    }

    fun deleteAllFavorites() = viewModelScope.launch {
        favoriteRepository.deleteAllFavoritePlaces()
    }

    fun getFavoritesByName(cityName: String) = viewModelScope.launch {
        favoriteRepository.getFavoritePlaceByName(cityName)
    }
}