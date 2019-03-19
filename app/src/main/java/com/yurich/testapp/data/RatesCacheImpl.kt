package com.yurich.testapp.data

import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatesCacheImpl @Inject constructor() : RatesCache {

    private val cachedRates = mutableMapOf(RatesRepository.BASE_CURRENCY to RatesRepository.DEFAULT_RATES)

    override fun updateRates(newRates: Map<String, Double>): Single<Map<String, Double>> {
        cachedRates.clear()
        cachedRates += newRates
        return Single.just(cachedRates)
    }

    override fun getRates(): Single<Map<String, Double>> =
            Single.just(cachedRates)
}