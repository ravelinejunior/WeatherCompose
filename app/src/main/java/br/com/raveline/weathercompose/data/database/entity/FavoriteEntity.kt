package br.com.raveline.weathercompose.data.database.entity

import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.raveline.weathercompose.utils.favoriteTableName

@Keep
@Entity(tableName = favoriteTableName)
data class FavoriteEntity(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val cityName: String,
    val countryName: String,
    val population: Int,
    val icon: String,
    val description: String,
    val cityId: Int

)
