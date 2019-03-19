package com.yurich.testapp.domain.trigger

import io.reactivex.Observable

interface EventTrigger {
    fun source(): Observable<Long>
}