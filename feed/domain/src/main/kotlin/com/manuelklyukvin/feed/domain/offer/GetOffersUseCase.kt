package com.manuelklyukvin.feed.domain.offer

class GetOffersUseCase(private val offerRepository: OfferRepository) {

    suspend operator fun invoke() = offerRepository.getOffers()
}