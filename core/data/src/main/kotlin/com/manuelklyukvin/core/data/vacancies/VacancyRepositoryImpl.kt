package com.manuelklyukvin.core.data.vacancies

import com.google.gson.Gson
import com.manuelklyukvin.core.data.vacancies.models.DataVacancyResponse
import com.manuelklyukvin.core.data.vacancies.models.toDomain
import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import com.manuelklyukvin.core.domain.result.models.Result
import com.manuelklyukvin.core.domain.vacancies.FavoritePreferencesManager
import com.manuelklyukvin.core.domain.vacancies.VacancyRepository
import com.manuelklyukvin.core.domain.vacancies.model.DomainVacancy

class VacancyRepositoryImpl(
    private val getDatabaseUseCase: GetDatabaseUseCase,
    private val favoritePreferencesManager: FavoritePreferencesManager
) : VacancyRepository {

    private var cachedDomainVacancyList: List<DomainVacancy>? = null

    override suspend fun getVacancies(): Result<List<DomainVacancy>, String?> {
        cachedDomainVacancyList?.let {
            return Result.Success(it)
        }

        return try {
            val database = getDatabaseUseCase()
            migrateFavoritesIfNeeded(database)

            val dataVacancyResponse = parseVacancyResponse(database)
            val favoriteIds = favoritePreferencesManager.getFavoriteIds().toSet()

            val domainVacancyList = dataVacancyResponse.dataVacancyList.map { dataVacancy ->
                dataVacancy.toDomain().copy(isFavorite = favoriteIds.contains(dataVacancy.id))
            }

            cachedDomainVacancyList = domainVacancyList
            Result.Success(domainVacancyList)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    override suspend fun getVacancyById(vacancyId: String): Result<DomainVacancy, String?> {
        return when (val vacanciesResult = getVacancies()) {
            is Result.Error -> Result.Error(vacanciesResult.error)
            is Result.Success -> {
                cachedDomainVacancyList?.find {
                    it.id == vacancyId
                }?.let {
                    Result.Success(it)
                } ?: Result.Error("Vacancy not found")
            }
        }
    }

    override suspend fun getFavoriteVacancies(): Result<List<DomainVacancy>, String?> {
        return when (val vacanciesResult = getVacancies()) {
            is Result.Error -> Result.Error(vacanciesResult.error)
            is Result.Success -> {
                val favoriteIds = favoritePreferencesManager.getFavoriteIds().toSet()
                val favoriteVacancyList = cachedDomainVacancyList?.filter {
                    favoriteIds.contains(it.id)
                }
                Result.Success(favoriteVacancyList.orEmpty())
            }
        }
    }

    override suspend fun toggleFavoriteStatus(vacancyId: String): Result<Unit, String?> {
        return when (val vacanciesResult = getVacancies()) {
            is Result.Error -> Result.Error(vacanciesResult.error)
            is Result.Success -> {
                cachedDomainVacancyList?.let { vacancies ->
                    vacancies.find {
                        it.id == vacancyId
                    }?.let { vacancy ->
                        val isCurrentlyFavorite = favoritePreferencesManager.getFavoriteIds().contains(vacancy.id)
                        val updatedFavoriteStatus = !isCurrentlyFavorite
                        favoritePreferencesManager.saveFavoriteVacancy(vacancyId, updatedFavoriteStatus)

                        cachedDomainVacancyList = vacancies.map {
                            if (it.id == vacancyId) {
                                it.copy(isFavorite = updatedFavoriteStatus)
                            } else {
                                it
                            }
                        }
                        Result.Success(Unit)
                    } ?: Result.Error("Vacancy not found")
                } ?: Result.Error("Vacancy list is empty")
            }
        }
    }

    private fun parseVacancyResponse(database: String) = Gson().fromJson(database, DataVacancyResponse::class.java)

    private suspend fun migrateFavoritesIfNeeded(database: String) {
        val migrationDone = favoritePreferencesManager.isInitialMigrationDone()
        if (migrationDone) return

        val dataVacancyResponse = parseVacancyResponse(database)
        val initialFavoriteIds = dataVacancyResponse.dataVacancyList
            .filter {
                it.isFavorite
            }
            .map {
                it.id
            }

        initialFavoriteIds.forEach { id ->
            favoritePreferencesManager.saveFavoriteVacancy(id, true)
        }

        favoritePreferencesManager.setInitialMigrationDone(true)
    }
}