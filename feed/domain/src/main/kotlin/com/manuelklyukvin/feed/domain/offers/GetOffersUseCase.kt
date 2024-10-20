package com.manuelklyukvin.feed.domain.offers

class GetOffersUseCase(private val offerRepository: OfferRepository) {

    suspend operator fun invoke() = offerRepository.getOffers()
}