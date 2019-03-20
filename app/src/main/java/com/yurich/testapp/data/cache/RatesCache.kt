package com.yurich.testapp.data.cache

interface RatesCache {
    fun updateRates(newRates: Map<String, Double>)
    fun getRates(): Map<String, Double>
}

