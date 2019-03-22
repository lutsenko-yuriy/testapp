package com.yurich.testapp.domain.choice

import com.yurich.testapp.domain.Currency
import com.yurich.testapp.domain.Currency.CREATOR.DEFAULT_CURRENCY
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentRateChoiceUseCaseImpl @Inject constructor() : CurrentRateChoiceUseCase {

    private val lastCurrencyInput = BehaviorSubject.createDefault(DEFAULT_CURRENCY)

    override fun lastCurrencyInput(): Observable<Currency> = lastCurrencyInput

    override fun setNextInput(currency: Currency) {
        lastCurrencyInput.onNext(currency)
    }
}