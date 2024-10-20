package com.manuelklyukvin.feed.domain.offer.model

data class DomainOffer(
    val id: String?,
    val title: String,
    val button: DomainButton?,
    val link: String
)

data class DomainButton(
    val text: String
)