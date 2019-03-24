package com.yurich.testapp.data

import com.yurich.testapp.data.RatesRepositoryImpl.Companion.BASE_CURRENCY
import com.yurich.testapp.data.RatesRepositoryImpl.Companion.DEFAULT_RATES
import com.yurich.testapp.data.cache.RatesCache
import com.yurich.testapp.data.network.RatesResponseBody
import com.yurich.testapp.data.network.RatesService
import io.reactivex.Single
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

    private val responseMap = mapOf("XYZ" to 2.0)
    private val cachedMap = mapOf("ABC" to 1.21)
    private val initialMap = mapOf(BASE_CURRENCY to DEFAULT_RATES)

    private val responseBody = RatesResponseBody(BASE_CURRENCY, "2019-12-21", responseMap)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(cache.getRates()).thenReturn(cachedMap)
        repository = RatesRepositoryImpl(service, cache)
    }

    @Test
    fun `should return data from network`() {
        `when`(service.getRates(BASE_CURRENCY)).thenReturn(Single.just(responseBody))

        repository.getRates()
                .test()
                .assertValue(responseMap + initialMap)
                .assertComplete()
    }

    @Test
    fun `should return data from local storage`() {
        `when`(service.getRates(BASE_CURRENCY)).thenReturn(Single.error(IllegalStateException()))

        repository.getRates()
                .test()
                .assertValue(cachedMap)
                .assertComplete()
    }
}