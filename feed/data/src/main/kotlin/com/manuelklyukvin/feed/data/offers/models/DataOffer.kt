package com.manuelklyukvin.feed.data.offers.models

import com.manuelklyukvin.feed.domain.offers.model.DomainButton
import com.manuelklyukvin.feed.domain.offers.model.DomainOffer

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