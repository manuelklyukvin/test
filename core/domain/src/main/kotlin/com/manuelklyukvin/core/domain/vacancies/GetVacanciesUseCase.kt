package com.manuelklyukvin.core.domain.vacancies

import com.manuelklyukvin.core.domain.result.Result
import com.manuelklyukvin.core.domain.vacancies.model.DomainVacancy

class GetVacanciesUseCase(
    private val vacancyRepository: VacancyRepository,
    private val formatPublishedDateUseCase: FormatPublishedDateUseCase
) {

    suspend operator fun invoke(): Result<List<DomainVacancy>, String?> {
        return when (val vacancyResult = vacancyRepository.getVacancies()) {
            is Result.Success -> {
                Result.Success(
                    vacancyResult.data.map { domainVacancy ->
                        domainVacancy.copy(publishedDate = formatPublishedDateUseCase(domainVacancy.publishedDate))
                    }
                )
            }
            is Result.Error -> Result.Error(vacancyResult.error)
        }
    }
}