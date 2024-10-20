package com.manuelklyukvin.feed.presentation.di

import android.content.Context
import com.manuelklyukvin.feed.data.offer.OfferRepositoryImpl
import com.manuelklyukvin.feed.domain.offer.GetOffersUseCase
import com.manuelklyukvin.feed.domain.offer.OfferRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object OfferModule {

    @Provides
    fun provideOfferRepository(@ApplicationContext context: Context): OfferRepository {
        return OfferRepositoryImpl(context)
    }

    @Provides
    fun provideGetOffersUseCase(offerRepository: OfferRepository) = GetOffersUseCase(offerRepository)
}