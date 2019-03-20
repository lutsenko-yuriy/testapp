package com.yurich.testapp.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesService {

    @GET("latest")
    fun getRates(@Query("base") baseCurrency: String): Single<RatesResponseBody>

}