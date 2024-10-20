package com.manuelklyukvin.feed.data.vacancy.models

import com.manuelklyukvin.feed.domain.vacancy.model.DomainAddress
import com.manuelklyukvin.feed.domain.vacancy.model.DomainExperience
import com.manuelklyukvin.feed.domain.vacancy.model.DomainSalary
import com.manuelklyukvin.feed.domain.vacancy.model.DomainVacancy

data class DataVacancy(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: DataAddress,
    val company: String,
    val experience: DataExperience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: DataSalary,
    val schedules: List<String>,
    val appliedNumber: Int,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>
)

data class DataAddress(
    val town: String,
    val street: String,
    val house: String
)

data class DataExperience(
    val previewText: String,
    val text: String
)

data class DataSalary(
    val short: String?,
    val full: String
)

fun DataVacancy.toDomain() = DomainVacancy(
    id = id,
    lookingNumber = lookingNumber,
    title = title,
    address = DomainAddress(
        town = address.town,
        street = address.street,
        house = address.house
    ),
    company = company,
    experience = DomainExperience(
        previewText = experience.previewText,
        text = experience.text
    ),
    publishedDate = publishedDate,
    isFavorite = isFavorite,
    salary = DomainSalary(
        short = salary.short,
        full = salary.full
    ),
    schedules = schedules,
    appliedNumber = appliedNumber,
    description = description,
    responsibilities = responsibilities,
    questions = questions
)