package com.manuelklyukvin.core.data.database

import android.content.Context
import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase

class GetDatabaseUseCaseImpl(private val context: Context) : GetDatabaseUseCase {

    override suspend operator fun invoke(): String {
        val inputStream = context.assets.open("database.json")
        return inputStream.bufferedReader().use {
            it.readText()
        }
    }
}