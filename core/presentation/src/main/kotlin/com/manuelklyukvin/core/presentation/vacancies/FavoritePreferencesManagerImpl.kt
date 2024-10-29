package com.manuelklyukvin.core.presentation.vacancies

import android.content.SharedPreferences
import com.manuelklyukvin.core.domain.vacancies.FavoritePreferencesManager
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FavoritePreferencesManagerImpl(
    private val sharedPreferences: SharedPreferences
) : FavoritePreferencesManager {

    override fun getFavoriteIds(): List<String> {
        val favoritesJson = sharedPreferences.getString("favorite_vacancies", null) ?: return emptyList()
        return Json.decodeFromString<List<String>>(favoritesJson)
    }

    override fun saveFavoriteVacancy(vacancyId: String, isFavorite: Boolean) {
        val currentFavorites = getFavoriteIds().toMutableList()

        if (isFavorite) {
            currentFavorites.add(vacancyId)
        } else {
            currentFavorites.remove(vacancyId)
        }

        sharedPreferences.edit().putString("favorite_vacancies", Json.encodeToString(currentFavorites)).apply()
    }

    override fun isInitialMigrationDone(): Boolean {
        return sharedPreferences.getBoolean("initialMigrationDone", false)
    }

    override fun setInitialMigrationDone(done: Boolean) {
        sharedPreferences.edit().putBoolean("initialMigrationDone", done).apply()
    }
}