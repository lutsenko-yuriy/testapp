package com.yurich.testapp.data

import com.yurich.testapp.data.cache.RatesCache
import com.yurich.testapp.data.network.RatesService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatesRepositoryImpl @Inject constructor(
        private val service: RatesService,
        private val cache: RatesCache
) : RatesRepository {

    @Suppress("RedundantLambdaArrow")
    override fun getRates() =
            service.getRates(BASE_CURRENCY)
                    .flatMap {
                        val newRates = it.rates + (BASE_CURRENCY to DEFAULT_RATES)
                        cache.updateRates(newRates)
                    }
                    .onErrorResumeNext(cache.getRates())

    companion object {
        const val BASE_CURRENCY = "EUR"
        const val DEFAULT_RATES = 1.0
    }
}