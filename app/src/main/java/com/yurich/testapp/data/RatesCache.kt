package com.yurich.testapp.data

import io.reactivex.Single

interface RatesCache {
    fun updateRates(newRates: Map<String, Double>): Single<Map<String, Double>>
    fun getRates(): Single<Map<String, Double>>
}

