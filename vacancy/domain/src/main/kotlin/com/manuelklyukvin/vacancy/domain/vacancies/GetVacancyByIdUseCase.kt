package com.manuelklyukvin.vacancy.domain.vacancies

import com.manuelklyukvin.core.domain.vacancies.VacancyRepository

class GetVacancyByIdUseCase(private val vacancyRepository: VacancyRepository) {

    suspend operator fun invoke(vacancyId: String) = vacancyRepository.getVacancyById(vacancyId)
}