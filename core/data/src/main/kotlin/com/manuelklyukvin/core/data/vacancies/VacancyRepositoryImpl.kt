package com.manuelklyukvin.core.data.vacancies

import com.google.gson.Gson
import com.manuelklyukvin.core.data.vacancies.models.DataVacancyResponse
import com.manuelklyukvin.core.data.vacancies.models.toDomain
import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import com.manuelklyukvin.core.domain.result.Result
import com.manuelklyukvin.core.domain.vacancies.VacancyRepository
import com.manuelklyukvin.core.domain.vacancies.model.DomainVacancy

class VacancyRepositoryImpl(private val getDatabaseUseCase: GetDatabaseUseCase) : VacancyRepository {

    override suspend fun getVacancies(): Result<List<DomainVacancy>, String?> {
        return try {
            val database = getDatabaseUseCase()
            val dataVacancyResponse = parseVacancyResponse(database)
            val domainVacancyList = dataVacancyResponse.dataVacancyList.map { dataVacancy ->
                dataVacancy.toDomain()
            }
            Result.Success(domainVacancyList)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    private fun parseVacancyResponse(database: String) = Gson().fromJson(database, DataVacancyResponse::class.java)
}