package com.manuelklyukvin.feed.presentation.di

import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import com.manuelklyukvin.feed.data.offer.OfferRepositoryImpl
import com.manuelklyukvin.feed.domain.offer.GetOffersUseCase
import com.manuelklyukvin.feed.domain.offer.OfferRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object OfferModule {

    @Provides
    fun provideOfferRepository(getDatabaseUseCase: GetDatabaseUseCase): OfferRepository {
        return OfferRepositoryImpl(getDatabaseUseCase)
    }

    @Provides
    fun provideGetOffersUseCase(offerRepository: OfferRepository) = GetOffersUseCase(offerRepository)
}