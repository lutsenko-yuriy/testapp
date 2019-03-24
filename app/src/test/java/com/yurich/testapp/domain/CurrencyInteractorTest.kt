package com.yurich.testapp.domain

import com.yurich.testapp.domain.choice.CurrentRateChoiceUseCase
import com.yurich.testapp.domain.rates.RatesUseCase
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CurrencyInteractorTest {

    @Mock
    lateinit var ratesUseCase: RatesUseCase

    @Mock
    lateinit var currentRateChoiceUseCase: CurrentRateChoiceUseCase

    lateinit var currencyInteractor: CurrencyInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        currencyInteractor = CurrencyInteractor(ratesUseCase, currentRateChoiceUseCase)
    }

    @Test
    fun `changes output on new currency input`() {
        `when`(ratesUseCase.ratesObservable()).thenReturn(
                Observable.just(mapOf("ABC" to 1.0, "DEF" to 2.0, "XYZ" to 3.0))
        )

        val inputsSubject = BehaviorSubject.create<Currency>()

        `when`(currentRateChoiceUseCase.lastCurrencyInput()).thenReturn(inputsSubject)

        val observer = currencyInteractor.pricesObservable().test()

        inputsSubject.onNext(Currency("ABC", 3.0))
        inputsSubject.onNext(Currency("DEF", 10.0))
        inputsSubject.onNext(Currency("XYZ", 21.0))

        inputsSubject.onComplete()

        observer.awaitCount(3)
                .assertValueCount(3)
                .assertValueSequence(listOf(
                        mapOf(
                                "ABC" to Currency("ABC", 3.0),
                                "DEF" to Currency("DEF", 6.0),
                                "XYZ" to Currency("XYZ", 9.0)
                        ),
                        mapOf(
                                "ABC" to Currency("ABC", 5.0),
                                "DEF" to Currency("DEF", 10.0),
                                "XYZ" to Currency("XYZ", 15.0)
                        ),
                        mapOf(
                                "ABC" to Currency("ABC", 7.0),
                                "DEF" to Currency("DEF", 14.0),
                                "XYZ" to Currency("XYZ", 21.0)
                        )
                ))
    }

    @Test
    fun `changes output on new rates`() {
        val ratesSubject = BehaviorSubject.create<Map<String, Double>>()

        `when`(ratesUseCase.ratesObservable()).thenReturn(ratesSubject)

        `when`(currentRateChoiceUseCase.lastCurrencyInput()).thenReturn(
                Observable.just(Currency("XYZ", 16.0))
        )

        val observer = currencyInteractor.pricesObservable().test()

        ratesSubject.onNext(mapOf("ABC" to 4.0, "DEF" to 6.0, "XYZ" to 2.0))
        ratesSubject.onNext(mapOf("ABC" to 8.0, "DEF" to 2.0, "XYZ" to 4.0))
        ratesSubject.onComplete()

        observer.awaitCount(2)
                .assertValueCount(2)
                .assertValueSequence(listOf(
                        mapOf(
                                "ABC" to Currency("ABC", 32.0),
                                "DEF" to Currency("DEF", 48.0),
                                "XYZ" to Currency("XYZ", 16.0)
                        ),
                        mapOf(
                                "ABC" to Currency("ABC", 32.0),
                                "DEF" to Currency("DEF", 8.0),
                                "XYZ" to Currency("XYZ", 16.0)
                        )
                ))
    }

}