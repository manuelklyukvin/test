package com.manuelklyukvin.feed.domain.offer

import com.manuelklyukvin.core.domain.result.Result
import com.manuelklyukvin.feed.domain.offer.model.DomainOffer

interface OfferRepository {

    suspend fun getOffers(): Result<List<DomainOffer>, String?>
}