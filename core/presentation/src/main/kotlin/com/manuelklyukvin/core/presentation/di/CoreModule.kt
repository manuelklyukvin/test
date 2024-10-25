package com.manuelklyukvin.core.presentation.di

import com.manuelklyukvin.core.data.database.GetDatabaseUseCaseImpl
import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CoreModule {

    @Provides
    fun provideGetDatabaseUseCase(): GetDatabaseUseCase {
        return GetDatabaseUseCaseImpl()
    }
}