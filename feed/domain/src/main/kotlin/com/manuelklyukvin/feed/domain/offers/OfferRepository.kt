package com.manuelklyukvin.feed.domain.offers

import com.manuelklyukvin.core.domain.result.Result
import com.manuelklyukvin.feed.domain.offers.model.DomainOffer

interface OfferRepository {

    suspend fun getOffers(): Result<List<DomainOffer>, String?>
}