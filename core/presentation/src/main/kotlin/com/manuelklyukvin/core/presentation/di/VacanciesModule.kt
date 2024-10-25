package com.manuelklyukvin.core.presentation.di

import com.manuelklyukvin.core.data.vacancies.VacancyRepositoryImpl
import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import com.manuelklyukvin.core.domain.vacancies.FormatPublishedDateUseCase
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
    fun provideFormatPublishedDateUseCase() = FormatPublishedDateUseCase()
}