package br.com.raveline.weathercompose.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raveline.weathercompose.data.database.entity.FavoriteEntity
import br.com.raveline.weathercompose.data.repository.implementations.FavoriteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepositoryImpl) :
    ViewModel() {

    private val mutableFavList = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favList get() = mutableFavList.asStateFlow()


    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favoriteRepository.getAllFavoritePlaces().distinctUntilChanged()
                    .collectLatest { favorites ->
                        if (favorites.isNotEmpty()) {
                            mutableFavList.value = favorites
                        } else {
                            Log.i("getFavorites", "Favorite list empty!")
                        }
                    }
            }
        }
    }

    fun insertFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        favoriteRepository.insertFavoritePlace(favoriteEntity = favoriteEntity)
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