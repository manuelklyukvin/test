package com.manuelklyukvin.search.presentation.screen.models

import com.manuelklyukvin.search.domain.offers.model.DomainOffer

data class Offer(
    val id: String?,
    val title: String,
    val link: String,
    val buttonText: String?
)

fun DomainOffer.toPresentation() = Offer(
    id = id,
    title = title,
    link = link,
    buttonText = button?.text
)