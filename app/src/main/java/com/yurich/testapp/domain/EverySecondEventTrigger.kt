package com.yurich.testapp.domain

import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EverySecondEventTrigger @Inject constructor() : EventTrigger {

    override fun source() = Observable.interval(1, TimeUnit.SECONDS)

}