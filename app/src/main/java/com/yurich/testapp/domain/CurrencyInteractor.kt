package com.yurich.testapp.domain

import com.yurich.testapp.domain.choice.CurrentRateChoiceUseCase
import com.yurich.testapp.domain.rates.RatesUseCase
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyInteractor @Inject constructor(
        private val ratesUseCase: RatesUseCase,
        private val currentChoice: CurrentRateChoiceUseCase
) {

    fun pricesObservable(): Observable<Map<String, Currency>> =
            Observables.combineLatest(
                    currentChoice.lastCurrencyInput(),
                    ratesUseCase.ratesObservable()
            ) { currency: Currency, rates: Map<String, Double> ->
                val multiplier = (currency.cost ?: 0.0) / (rates[currency.code] ?: 1.0)
                rates.mapValues { rate ->
                    Currency(rate.key, rate.value * multiplier)
                }
            }
                    .subscribeOn(Schedulers.computation())

    fun setNextInput(currency: Currency) {
        currentChoice.setNextInput(currency)
    }
}