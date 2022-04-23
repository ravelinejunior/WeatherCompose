package br.com.raveline.weathercompose.data.repository.implementations

import androidx.annotation.WorkerThread
import br.com.raveline.weathercompose.data.database.dao.FavoriteDao
import br.com.raveline.weathercompose.data.database.entity.FavoriteEntity
import br.com.raveline.weathercompose.data.repository.FavoritePlaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val favoriteDao: FavoriteDao) :
    FavoritePlaceRepository {

    @WorkerThread
    override suspend fun insertFavoritePlace(favoriteEntity: FavoriteEntity) {
        favoriteDao.insertFavoritePlace(favoriteEntity)
    }

    @WorkerThread
    override suspend fun updateFavoritePlace(favoriteEntity: FavoriteEntity) {
        favoriteDao.updateFavoritePlace(favoriteEntity)
    }

    @WorkerThread
    override suspend fun deleteFavoritePlace(favoriteEntity: FavoriteEntity) {
        favoriteDao.deleteFavoritePlace(favoriteEntity)
    }

    @WorkerThread
    override suspend fun deleteAllFavoritePlaces() {
        favoriteDao.deleteAllPlaces()
    }

    @WorkerThread
    override fun getAllFavoritePlaces(): Flow<List<FavoriteEntity>> {
        return favoriteDao.getAllFavorites()
    }

    @WorkerThread
    override fun getFavoritePlaceByName(cityName: String): Flow<List<FavoriteEntity>> {
        return favoriteDao.getFavoritePlaceByName(cityName)
    }
}