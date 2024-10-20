package com.manuelklyukvin.feed.presentation.di

import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import com.manuelklyukvin.feed.data.vacancy.VacancyRepositoryImpl
import com.manuelklyukvin.feed.domain.vacancy.GetVacanciesUseCase
import com.manuelklyukvin.feed.domain.vacancy.VacancyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object VacancyModule {

    @Provides
    fun provideVacancyRepository(getDatabaseUseCase: GetDatabaseUseCase): VacancyRepository {
        return VacancyRepositoryImpl(getDatabaseUseCase)
    }

    @Provides
    fun provideGetVacanciesUseCase(vacancyRepository: VacancyRepository) = GetVacanciesUseCase(vacancyRepository)
}