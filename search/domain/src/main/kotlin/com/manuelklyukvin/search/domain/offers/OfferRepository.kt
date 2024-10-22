package com.manuelklyukvin.search.domain.offers

import com.manuelklyukvin.core.domain.result.models.Result
import com.manuelklyukvin.search.domain.offers.model.DomainOffer

interface OfferRepository {

    suspend fun getOffers(): Result<List<DomainOffer>, String?>
}