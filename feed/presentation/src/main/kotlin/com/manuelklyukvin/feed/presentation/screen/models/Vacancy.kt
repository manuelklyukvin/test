package com.manuelklyukvin.feed.presentation.screen.models

import com.manuelklyukvin.feed.domain.vacancy.model.DomainVacancy

data class Vacancy(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>
)

data class Address(
    val town: String,
    val street: String,
    val house: String
)

data class Experience(
    val previewText: String,
    val text: String
)

data class Salary(
    val short: String?,
    val full: String
)

fun DomainVacancy.toPresentation() = Vacancy(
    id = id,
    lookingNumber = lookingNumber,
    title = title,
    address = Address(
        town = address.town,
        street = address.street,
        house = address.house
    ),
    company = company,
    experience = Experience(
        previewText = experience.previewText,
        text = experience.text
    ),
    publishedDate = publishedDate,
    isFavorite = isFavorite,
    salary = Salary(
        short = salary.short,
        full = salary.full
    ),
    schedules = schedules,
    appliedNumber = appliedNumber,
    description = description,
    responsibilities = responsibilities,
    questions = questions
)