package com.yurich.testapp.domain.choice

import com.yurich.testapp.domain.Currency
import com.yurich.testapp.domain.Currency.CREATOR.DEFAULT_CURRENCY
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentRateChoiceUseCase @Inject constructor() {

    private val lastCurrencyInput = BehaviorSubject.createDefault(DEFAULT_CURRENCY)

    fun lastCurrencyInput(): Observable<Currency> = lastCurrencyInput

    fun setNextInput(currency: Currency) {
        lastCurrencyInput.onNext(currency)
    }
}