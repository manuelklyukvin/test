package com.manuelklyukvin.vacancy.presentation.di

import com.manuelklyukvin.core.domain.vacancies.VacancyRepository
import com.manuelklyukvin.vacancy.domain.vacancies.GetVacancyByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object VacancyModule {

    @Provides
    fun provideGetVacancyByIdUseCase(vacancyRepository: VacancyRepository): GetVacancyByIdUseCase {
        return GetVacancyByIdUseCase(vacancyRepository)
    }
}