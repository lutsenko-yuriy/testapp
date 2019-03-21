package com.yurich.testapp.domain.rates.trigger

import io.reactivex.Observable

interface EventTrigger {
    fun source(): Observable<Long>
}