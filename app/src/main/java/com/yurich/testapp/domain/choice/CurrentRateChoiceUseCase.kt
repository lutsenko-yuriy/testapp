package com.yurich.testapp.domain.choice

import com.yurich.testapp.domain.Currency
import io.reactivex.Observable

interface CurrentRateChoiceUseCase {
    fun lastCurrencyInput(): Observable<Currency>
    fun setNextInput(currency: Currency)
}
