package com.manuelklyukvin.core.presentation.di

import android.content.Context
import android.content.SharedPreferences
import com.manuelklyukvin.core.data.vacancies.VacancyRepositoryImpl
import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import com.manuelklyukvin.core.domain.vacancies.FavoritePreferencesManager
import com.manuelklyukvin.core.domain.vacancies.FormatPublishedDateUseCase
import com.manuelklyukvin.core.domain.vacancies.ToggleFavoriteStatusUseCase
import com.manuelklyukvin.core.domain.vacancies.VacancyRepository
import com.manuelklyukvin.core.presentation.vacancies.FavoritePreferencesManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object VacanciesModule {

    @Provides
    fun provideVacancyRepository(
        getDatabaseUseCase: GetDatabaseUseCase,
        favoritePreferencesManager: FavoritePreferencesManager
    ): VacancyRepository = VacancyRepositoryImpl(
        getDatabaseUseCase = getDatabaseUseCase,
        favoritePreferencesManager = favoritePreferencesManager
    )

    @Provides
    fun provideFormatPublishedDateUseCase() = FormatPublishedDateUseCase()

    @Provides
    fun provideToggleFavoriteStatusUseCase(vacancyRepository: VacancyRepository): ToggleFavoriteStatusUseCase {
        return ToggleFavoriteStatusUseCase(vacancyRepository)
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("favorite_vacancies", Context.MODE_PRIVATE)
    }

    @Provides
    fun providePreferencesManager(sharedPreferences: SharedPreferences): FavoritePreferencesManager {
        return FavoritePreferencesManagerImpl(sharedPreferences)
    }
}