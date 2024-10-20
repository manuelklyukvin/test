package com.manuelklyukvin.feed.presentation.screen.models

import com.manuelklyukvin.feed.domain.offer.model.DomainOffer

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