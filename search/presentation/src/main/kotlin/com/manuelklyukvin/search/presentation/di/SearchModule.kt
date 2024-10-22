package com.manuelklyukvin.search.presentation.di

import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import com.manuelklyukvin.core.domain.vacancies.FormatPublishedDateUseCase
import com.manuelklyukvin.core.domain.vacancies.VacancyRepository
import com.manuelklyukvin.search.data.offers.OfferRepositoryImpl
import com.manuelklyukvin.search.domain.offers.GetOffersUseCase
import com.manuelklyukvin.search.domain.offers.OfferRepository
import com.manuelklyukvin.search.domain.vacancies.GetVacanciesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SearchModule {

    @Provides
    fun provideOfferRepository(getDatabaseUseCase: GetDatabaseUseCase): OfferRepository {
        return OfferRepositoryImpl(getDatabaseUseCase)
    }

    @Provides
    fun provideGetOffersUseCase(offerRepository: OfferRepository) = GetOffersUseCase(offerRepository)

    @Provides
    fun provideGetVacanciesUseCase(
        vacancyRepository: VacancyRepository,
        formatPublishedDateUseCase: FormatPublishedDateUseCase
    ) = GetVacanciesUseCase(
        vacancyRepository = vacancyRepository,
        formatPublishedDateUseCase = formatPublishedDateUseCase
    )
}