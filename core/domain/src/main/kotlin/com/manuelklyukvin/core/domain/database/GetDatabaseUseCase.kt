package com.manuelklyukvin.core.domain.database

interface GetDatabaseUseCase {

    suspend operator fun invoke(): String
}