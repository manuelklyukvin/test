package com.manuelklyukvin.core.domain.vacancies

class ToggleFavoriteStatusUseCase(private val vacancyRepository: VacancyRepository) {

    suspend operator fun invoke(vacancyId: String) = vacancyRepository.toggleFavoriteStatus(vacancyId)
}