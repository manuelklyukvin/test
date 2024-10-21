package com.manuelklyukvin.core.domain.vacancies

import com.manuelklyukvin.core.domain.result.Result
import com.manuelklyukvin.core.domain.vacancies.model.DomainVacancy

class GetVacancyByIdUseCase(
    private val vacancyRepository: VacancyRepository,
    private val formatPublishedDateUseCase: FormatPublishedDateUseCase
) {

    suspend operator fun invoke(vacancyId: String): Result<DomainVacancy, String?> {
        return when (val vacancyResult = vacancyRepository.getVacancyById(vacancyId)) {
            is Result.Success -> {
                Result.Success(
                    vacancyResult.data.copy(
                        publishedDate = formatPublishedDateUseCase(vacancyResult.data.publishedDate)
                    )
                )
            }
            is Result.Error -> Result.Error(vacancyResult.error)
        }
    }
}