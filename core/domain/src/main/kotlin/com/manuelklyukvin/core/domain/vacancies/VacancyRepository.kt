package com.manuelklyukvin.core.domain.vacancies

import com.manuelklyukvin.core.domain.result.Result
import com.manuelklyukvin.core.domain.vacancies.model.DomainVacancy

interface VacancyRepository {

    suspend fun getVacancies(): Result<List<DomainVacancy>, String?>

    suspend fun getVacancyById(vacancyId: String): Result<DomainVacancy, String?>
}