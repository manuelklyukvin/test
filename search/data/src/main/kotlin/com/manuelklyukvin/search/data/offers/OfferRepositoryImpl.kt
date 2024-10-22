package com.manuelklyukvin.search.data.offers

import com.google.gson.Gson
import com.manuelklyukvin.core.domain.database.GetDatabaseUseCase
import com.manuelklyukvin.core.domain.result.models.Result
import com.manuelklyukvin.search.data.offers.models.DataOfferResponse
import com.manuelklyukvin.search.data.offers.models.toDomain
import com.manuelklyukvin.search.domain.offers.OfferRepository
import com.manuelklyukvin.search.domain.offers.model.DomainOffer

class OfferRepositoryImpl(private val getDatabaseUseCase: GetDatabaseUseCase) : OfferRepository {

    private var cachedDomainOfferList: List<DomainOffer>? = null

    override suspend fun getOffers(): Result<List<DomainOffer>, String?> {
        cachedDomainOfferList?.let {
            return Result.Success(it)
        }

        return try {
            val database = getDatabaseUseCase()
            val dataOfferResponse = parseOfferResponse(database)
            val domainOfferList = dataOfferResponse.dataOfferList.map { dataOffer ->
                dataOffer.toDomain()
            }
            cachedDomainOfferList = domainOfferList
            Result.Success(domainOfferList)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    private fun parseOfferResponse(database: String) = Gson().fromJson(database, DataOfferResponse::class.java)
}