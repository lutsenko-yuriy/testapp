package com.yurich.testapp.data

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET

interface RatesService {

    @GET("latest")
    fun getRates(@Field("base") baseCurrency: String): Single<RatesResponseBody>

}