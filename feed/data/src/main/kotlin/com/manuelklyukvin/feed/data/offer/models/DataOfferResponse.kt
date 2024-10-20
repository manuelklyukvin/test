package com.manuelklyukvin.feed.data.offer.models

import com.google.gson.annotations.SerializedName

data class DataOfferResponse(
    @SerializedName("offers") val dataOfferList: List<DataOffer>
)