package com.yurich.testapp.domain

import com.yurich.testapp.data.RatesRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatesUseCase @Inject constructor(
        private val eventTrigger: EventTrigger,
        private val repository: RatesRepository
) {

    fun ratesObservable() =
            eventTrigger.source().switchMap {
                repository.getRates()
                        .subscribeOn(Schedulers.io())
                        .toObservable()
            }
}