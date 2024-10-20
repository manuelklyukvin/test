package com.manuelklyukvin.feed.data.vacancy.models

import com.google.gson.annotations.SerializedName

data class DataVacancyResponse(
    @SerializedName("vacancies") val dataVacancyList: List<DataVacancy>
)