package com.yurich.testapp.data

import com.google.gson.annotations.SerializedName

data class CurrencyResponseBody(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Map<String, Double>
)
