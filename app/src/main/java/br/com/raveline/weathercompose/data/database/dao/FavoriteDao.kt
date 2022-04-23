package br.com.raveline.weathercompose.data.database.dao

import androidx.room.*
import br.com.raveline.weathercompose.data.database.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
sealed interface FavoriteDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritePlace(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavoritePlace(favoriteEntity: FavoriteEntity)

    @Update
    suspend fun updateFavoritePlace(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM FAVORITE_PLACE_TABLE ORDER BY ID DESC")
    fun getAllFavorites():Flow<List<FavoriteEntity>>

    @Query("DELETE FROM FAVORITE_PLACE_TABLE")
    suspend fun deleteAllPlaces()
}