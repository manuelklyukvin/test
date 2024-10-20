package com.manuelklyukvin.feed.data.offer

import android.content.Context
import com.google.gson.Gson
import com.manuelklyukvin.core.domain.result.Result
import com.manuelklyukvin.feed.data.offer.models.DataOfferResponse
import com.manuelklyukvin.feed.data.offer.models.toDomain
import com.manuelklyukvin.feed.domain.offer.OfferRepository
import com.manuelklyukvin.feed.domain.offer.model.DomainOffer

class OfferRepositoryImpl(private val context: Context) : OfferRepository {

    override suspend fun getOffers(): Result<List<DomainOffer>, String?> {
        return try {
            val jsonString = loadJsonFromAssets()
            val dataOfferResponse = parseDataOfferResponse(jsonString)
            val domainOfferList = dataOfferResponse.dataOfferList.map { dataOffer ->
                dataOffer.toDomain()
            }
            Result.Success(domainOfferList)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    private fun loadJsonFromAssets(): String {
        val inputStream = context.assets.open("core_data.json")
        return inputStream.bufferedReader().use {
            it.readText()
        }
    }

    private fun parseDataOfferResponse(jsonString: String): DataOfferResponse {
        return Gson().fromJson(jsonString, DataOfferResponse::class.java)
    }
}