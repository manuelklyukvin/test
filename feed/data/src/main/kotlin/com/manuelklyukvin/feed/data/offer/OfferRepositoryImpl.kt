package com.manuelklyukvin.feed.data.offer

import com.google.gson.Gson
import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import com.manuelklyukvin.core.domain.result.Result
import com.manuelklyukvin.feed.data.offer.models.DataOfferResponse
import com.manuelklyukvin.feed.data.offer.models.toDomain
import com.manuelklyukvin.feed.domain.offer.OfferRepository
import com.manuelklyukvin.feed.domain.offer.model.DomainOffer

class OfferRepositoryImpl(private val getDatabaseUseCase: GetDatabaseUseCase) : OfferRepository {

    override suspend fun getOffers(): Result<List<DomainOffer>, String?> {
        return try {
            val database = getDatabaseUseCase()
            val dataOfferResponse = parseOfferResponse(database)
            val domainOfferList = dataOfferResponse.dataOfferList.map { dataOffer ->
                dataOffer.toDomain()
            }
            Result.Success(domainOfferList)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    private fun parseOfferResponse(database: String) = Gson().fromJson(database, DataOfferResponse::class.java)
}