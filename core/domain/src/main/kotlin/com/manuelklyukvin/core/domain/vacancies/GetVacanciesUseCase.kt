package com.manuelklyukvin.core.domain.vacancies

class GetVacanciesUseCase(private val vacancyRepository: VacancyRepository) {

    suspend operator fun invoke() = vacancyRepository.getVacancies()
}