package com.manuelklyukvin.feed.domain.vacancy

import com.manuelklyukvin.core.domain.result.Result
import com.manuelklyukvin.feed.domain.vacancy.model.DomainVacancy

interface VacancyRepository {

    suspend fun getVacancies(): Result<List<DomainVacancy>, String?>
}