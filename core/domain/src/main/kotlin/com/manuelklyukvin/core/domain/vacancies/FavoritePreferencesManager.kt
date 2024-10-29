package com.manuelklyukvin.core.domain.vacancies

interface FavoritePreferencesManager {

    fun getFavoriteIds(): List<String>

    fun saveFavoriteVacancy(vacancyId: String, isFavorite: Boolean)

    fun isInitialMigrationDone(): Boolean

    fun setInitialMigrationDone(done: Boolean)
}