package com.manuelklyukvin.core.di

import android.content.Context
import com.manuelklyukvin.core.data.database.GetDatabaseUseCaseImpl
import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object CoreModule {

    @Provides
    fun provideGetDatabaseUseCase(@ApplicationContext context: Context): GetDatabaseUseCase {
        return GetDatabaseUseCaseImpl(context)
    }
}