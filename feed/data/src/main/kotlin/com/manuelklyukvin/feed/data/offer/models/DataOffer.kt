package com.manuelklyukvin.feed.data.offer.models

import com.manuelklyukvin.feed.domain.offer.model.DomainButton
import com.manuelklyukvin.feed.domain.offer.model.DomainOffer

data class DataOffer(
    val id: String?,
    val title: String,
    val button: DataButton?,
    val link: String
)

data class DataButton(
    val text: String
)

fun DataOffer.toDomain() = DomainOffer(
    id = id,
    title = title,
    button = button?.text?.let { DomainButton(it) },
    link = link
)