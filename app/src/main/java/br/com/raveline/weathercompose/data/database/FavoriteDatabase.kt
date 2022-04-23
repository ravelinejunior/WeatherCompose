package br.com.raveline.weathercompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.raveline.weathercompose.data.database.dao.FavoriteDao
import br.com.raveline.weathercompose.data.database.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = true
)
abstract class FavoriteDatabase :RoomDatabase() {
    abstract fun favoriteDao():FavoriteDao
}