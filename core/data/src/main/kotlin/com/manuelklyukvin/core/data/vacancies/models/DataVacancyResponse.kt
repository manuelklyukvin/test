package com.manuelklyukvin.core.data.vacancies.models

import com.google.gson.annotations.SerializedName

data class DataVacancyResponse(
    @SerializedName("vacancies") val dataVacancyList: List<DataVacancy>
)