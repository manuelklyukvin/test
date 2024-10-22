package com.manuelklyukvin.search.domain.offers

class GetOffersUseCase(private val offerRepository: OfferRepository) {

    suspend operator fun invoke() = offerRepository.getOffers()
}