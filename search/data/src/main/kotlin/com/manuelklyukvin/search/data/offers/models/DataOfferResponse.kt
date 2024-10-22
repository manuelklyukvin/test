package com.manuelklyukvin.search.data.offers.models

import com.google.gson.annotations.SerializedName

data class DataOfferResponse(
    @SerializedName("offers") val dataOfferList: List<DataOffer>
)