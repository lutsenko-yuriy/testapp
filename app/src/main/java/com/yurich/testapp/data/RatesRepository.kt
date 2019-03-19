package com.yurich.testapp.data

import io.reactivex.Single

interface RatesRepository {
    fun getRates(): Single<Map<String, Double>>
}