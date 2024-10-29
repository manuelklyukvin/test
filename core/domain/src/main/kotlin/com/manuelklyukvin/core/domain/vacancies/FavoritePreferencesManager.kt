package com.manuelklyukvin.core.domain.vacancies

interface FavoritePreferencesManager {

    suspend fun getFavoriteIds(): List<String>

    suspend fun saveFavoriteVacancy(vacancyId: String, isFavorite: Boolean)

    suspend fun isInitialMigrationDone(): Boolean

    suspend fun setInitialMigrationDone(done: Boolean)
}