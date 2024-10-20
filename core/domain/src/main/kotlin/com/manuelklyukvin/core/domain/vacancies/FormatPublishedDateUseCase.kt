package com.manuelklyukvin.core.domain.vacancies

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class FormatPublishedDateUseCase {

    operator fun invoke(dateString: String): String {
        val date = LocalDate.parse(dateString)
        val currentYear = LocalDate.now().year

        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))

        return if (date.year == currentYear) {
            date.format(formatter)
        } else {
            val formatterWithYear = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
            date.format(formatterWithYear)
        }
    }
}