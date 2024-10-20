package com.manuelklyukvin.feed.domain.vacancy

class GetVacanciesUseCase(private val vacancyRepository: VacancyRepository) {

    suspend operator fun invoke() = vacancyRepository.getVacancies()
}