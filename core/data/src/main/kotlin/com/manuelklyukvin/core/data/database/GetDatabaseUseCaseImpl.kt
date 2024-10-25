package com.manuelklyukvin.core.data.database

import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import java.io.FileNotFoundException

class GetDatabaseUseCaseImpl : GetDatabaseUseCase {

    override suspend operator fun invoke(): String {
        val inputStream = this::class.java.classLoader?.getResourceAsStream("database.json")
            ?: throw FileNotFoundException("File not found: database.json")

        return inputStream.bufferedReader().use {
            it.readText()
        }
    }
}