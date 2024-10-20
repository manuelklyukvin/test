package com.manuelklyukvin.core.domain.vacancies.model

data class DomainVacancy(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: DomainAddress,
    val company: String,
    val experience: DomainExperience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: DomainSalary,
    val schedules: List<String>,
    val appliedNumber: Int,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>
)

data class DomainAddress(
    val town: String,
    val street: String,
    val house: String
)

data class DomainExperience(
    val previewText: String,
    val text: String
)

data class DomainSalary(
    val short: String?,
    val full: String
)