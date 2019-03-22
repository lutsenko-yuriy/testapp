package com.yurich.testapp.domain.rates

import io.reactivex.Observable

interface RatesUseCase {

    fun ratesObservable(): Observable<Map<String, Double>>
}
