package com.yurich.testapp.data.cache

import com.yurich.testapp.data.RatesRepositoryImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatesCacheImpl @Inject constructor() : RatesCache {

    private val cachedRates = mutableMapOf(RatesRepositoryImpl.BASE_CURRENCY to RatesRepositoryImpl.DEFAULT_RATES)

    override fun updateRates(newRates: Map<String, Double>) {
        cachedRates.clear()
        cachedRates += newRates
    }

    override fun getRates() = cachedRates
}