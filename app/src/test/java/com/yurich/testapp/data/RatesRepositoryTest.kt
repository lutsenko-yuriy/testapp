package com.yurich.testapp.data

import com.yurich.testapp.data.RatesRepositoryImpl.Companion.BASE_CURRENCY
import com.yurich.testapp.data.RatesRepositoryImpl.Companion.DEFAULT_RATES
import com.yurich.testapp.data.cache.RatesCache
import com.yurich.testapp.data.network.RatesResponseBody
import com.yurich.testapp.data.network.RatesService
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RatesRepositoryTest {

    @Mock
    lateinit var service: RatesService

    @Mock
    lateinit var cache: RatesCache

    lateinit var repository: RatesRepositoryImpl

    lateinit var observer: TestObserver<Map<String, Double>>

    private val responseMap = mapOf("XYZ" to 2.0)
    private val cachedMap = mapOf("ABC" to 1.21)
    private val initialMap = mapOf(BASE_CURRENCY to DEFAULT_RATES)

    private val responseBody = RatesResponseBody(BASE_CURRENCY, "2019-12-21", responseMap)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(cache.getRates()).thenReturn(cachedMap)
        observer = TestObserver()
        repository = RatesRepositoryImpl(service, cache)
    }

    @Test
    fun shouldReturnDataFromNetwork() {
        `when`(service.getRates(BASE_CURRENCY)).thenReturn(Single.just(responseBody))

        repository.getRates()
                .subscribe(observer)

        observer.assertValue(responseMap + initialMap)
        observer.assertComplete()
    }

    @Test
    fun shouldReturnDataFromLocalStorage() {
        `when`(service.getRates(BASE_CURRENCY)).thenReturn(Single.error(IllegalStateException()))

        repository.getRates()
                .subscribe(observer)

        observer.assertValue(cachedMap)
        observer.assertComplete()
    }
}