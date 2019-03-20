package com.yurich.testapp.ui.view

import com.yurich.testapp.ui.presenter.Currency

interface CurrencyView {
    fun displayCurrencies(newCurrencies: Map<String, Currency>)
}