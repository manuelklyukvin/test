package com.manuelklyukvin.core.di

import com.manuelklyukvin.core.data.vacancies.VacancyRepositoryImpl
import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import com.manuelklyukvin.core.domain.vacancies.FormatPublishedDateUseCase
import com.manuelklyukvin.core.domain.vacancies.GetVacanciesUseCase
import com.manuelklyukvin.core.domain.vacancies.GetVacancyByIdUseCase
import com.manuelklyukvin.core.domain.vacancies.VacancyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object VacanciesModule {

    @Provides
    fun provideVacancyRepository(getDatabaseUseCase: GetDatabaseUseCase): VacancyRepository {
        return VacancyRepositoryImpl(getDatabaseUseCase)
    }

    @Provides
    fun provideGetVacanciesUseCase(
        vacancyRepository: VacancyRepository,
        formatPublishedDateUseCase: FormatPublishedDateUseCase
    ) = GetVacanciesUseCase(
        vacancyRepository = vacancyRepository,
        formatPublishedDateUseCase = formatPublishedDateUseCase
    )

    @Provides
    fun provideGetVacancyByIdUseCase(
        vacancyRepository: VacancyRepository,
        formatPublishedDateUseCase: FormatPublishedDateUseCase
    ) = GetVacancyByIdUseCase(
        vacancyRepository = vacancyRepository,
        formatPublishedDateUseCase = formatPublishedDateUseCase
    )

    @Provides
    fun provideFormatPublishedDateUseCase() = FormatPublishedDateUseCase()
}