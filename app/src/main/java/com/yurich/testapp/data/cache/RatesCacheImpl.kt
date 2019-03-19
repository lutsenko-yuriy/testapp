package com.yurich.testapp.data.cache

import com.yurich.testapp.data.RatesRepositoryImpl
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatesCacheImpl @Inject constructor() : RatesCache {

    private val cachedRates = mutableMapOf(RatesRepositoryImpl.BASE_CURRENCY to RatesRepositoryImpl.DEFAULT_RATES)

    override fun updateRates(newRates: Map<String, Double>): Single<Map<String, Double>> {
        cachedRates.clear()
        cachedRates += newRates
        return Single.just(cachedRates)
    }

    override fun getRates(): Single<Map<String, Double>> =
            Single.just(cachedRates)
}