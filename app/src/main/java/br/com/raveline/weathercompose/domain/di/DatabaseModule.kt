package br.com.raveline.weathercompose.domain.di

import android.content.Context
import androidx.room.Room
import br.com.raveline.weathercompose.data.database.FavoriteDatabase
import br.com.raveline.weathercompose.data.database.dao.FavoriteDao
import br.com.raveline.weathercompose.utils.favoriteDatabaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesFavoriteDatabase(@ApplicationContext context: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FavoriteDatabase::class.java,
            favoriteDatabaseName
        ).enableMultiInstanceInvalidation()
            .allowMainThreadQueries()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    @Provides
    @Singleton
    fun providesFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoriteDao =
        favoriteDatabase.favoriteDao()
}