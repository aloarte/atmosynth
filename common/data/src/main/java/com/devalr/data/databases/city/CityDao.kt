package com.devalr.data.databases.city

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Insert
    suspend fun insertCities(city: List<CityEntity>)

    @Query("SELECT * FROM cities")
    suspend fun getCities(): List<CityEntity>

    @Query("SELECT * FROM cities WHERE selected=1")
    suspend fun getSelectedCities(): List<CityEntity>

    @Query("UPDATE cities SET selected = 1 WHERE name = :cityName")
    suspend fun selectCity(cityName: String)

    @Query("UPDATE cities SET selected = 0 WHERE name = :cityName")
    suspend fun unselectCity(cityName: String)

    @Query("UPDATE cities SET active = 0")
    suspend fun deactivateCities()

    @Query("UPDATE cities SET active = 1 WHERE name = :cityName")
    suspend fun activateCity(cityName: String)

    @Query("SELECT * FROM cities WHERE name = :cityName")
    suspend fun getCityDataByName(cityName: String): CityEntity?

    @Query("SELECT COUNT(*) FROM cities")
    suspend fun getCitiesCount(): Int

    @Query("DELETE FROM cities")
    suspend fun removeAllDailyPrompts()

    suspend fun isDatabaseFilled() = getCitiesCount() > 0
}