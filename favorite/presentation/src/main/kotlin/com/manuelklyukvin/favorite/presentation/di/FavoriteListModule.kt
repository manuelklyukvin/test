package com.manuelklyukvin.favorite.presentation.di

import com.manuelklyukvin.core.domain.vacancies.FormatPublishedDateUseCase
import com.manuelklyukvin.core.domain.vacancies.VacancyRepository
import com.manuelklyukvin.favorite.domain.vacancies.GetFavoriteVacanciesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FavoriteListModule {

    @Provides
    fun provideGetFavoriteVacanciesUseCase(
        vacancyRepository: VacancyRepository,
        formatPublishedDateUseCase: FormatPublishedDateUseCase
    ) = GetFavoriteVacanciesUseCase(
        vacancyRepository = vacancyRepository,
        formatPublishedDateUseCase = formatPublishedDateUseCase
    )
}