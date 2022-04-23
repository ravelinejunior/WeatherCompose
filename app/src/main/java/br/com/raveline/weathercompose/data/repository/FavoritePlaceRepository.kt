package br.com.raveline.weathercompose.data.repository

import br.com.raveline.weathercompose.data.database.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoritePlaceRepository {
    suspend fun insertFavoritePlace(favoriteEntity: FavoriteEntity)
    suspend fun updateFavoritePlace(favoriteEntity: FavoriteEntity)
    suspend fun deleteFavoritePlace(favoriteEntity: FavoriteEntity)
    suspend fun deleteAllFavoritePlaces()
    fun getAllFavoritePlaces(): Flow<List<FavoriteEntity>>
    fun getFavoritePlaceByName(cityName: String): Flow<List<FavoriteEntity>>
}