package com.yurich.testapp.ui.adapters

interface CurrencyInteractionListener {

    fun onCurrencyChanged(currencyCode: String, amount: Double)

    fun onCurrencySelected(currencyCode: String)

}