package com.manuelklyukvin.core.data.vacancies

import com.google.gson.Gson
import com.manuelklyukvin.core.data.vacancies.models.DataVacancyResponse
import com.manuelklyukvin.core.data.vacancies.models.toDomain
import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import com.manuelklyukvin.core.domain.result.models.Result
import com.manuelklyukvin.core.domain.vacancies.VacancyRepository
import com.manuelklyukvin.core.domain.vacancies.model.DomainVacancy

class VacancyRepositoryImpl(private val getDatabaseUseCase: GetDatabaseUseCase) : VacancyRepository {

    private var cachedDomainVacancyList: List<DomainVacancy>? = null

    override suspend fun getVacancies(): Result<List<DomainVacancy>, String?> {
        cachedDomainVacancyList?.let {
            return Result.Success(it)
        }

        return try {
            val database = getDatabaseUseCase()
            val dataVacancyResponse = parseVacancyResponse(database)
            val domainVacancyList = dataVacancyResponse.dataVacancyList.map { dataVacancy ->
                dataVacancy.toDomain()
            }
            cachedDomainVacancyList = domainVacancyList
            Result.Success(domainVacancyList)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    override suspend fun getVacancyById(vacancyId: String): Result<DomainVacancy, String?> {
        val vacanciesResult = getVacancies()
        if (vacanciesResult is Result.Error) {
            return Result.Error(vacanciesResult.error)
        }

        val vacancy = cachedDomainVacancyList?.find { domainVacancy ->
            domainVacancy.id == vacancyId
        }
        return vacancy?.let {
            Result.Success(it)
        } ?: Result.Error(null)
    }

    override suspend fun getFavoriteVacancies(): Result<List<DomainVacancy>, String?> {
        val vacanciesResult = getVacancies()
        if (vacanciesResult is Result.Error) {
            return Result.Error(vacanciesResult.error)
        }

        val favoriteVacancyList = cachedDomainVacancyList?.filter {
            it.isFavorite
        }

        return favoriteVacancyList?.let {
            Result.Success(it)
        } ?: Result.Error(null)
    }

    private fun parseVacancyResponse(database: String) = Gson().fromJson(database, DataVacancyResponse::class.java)
}