package com.yurich.testapp.ui.view

import com.yurich.testapp.domain.Currency

interface CurrencyView {
    fun displayCurrencies(newCurrencies: Map<String, Currency>)
}