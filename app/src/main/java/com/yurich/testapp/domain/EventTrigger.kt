package com.yurich.testapp.domain

import io.reactivex.Observable

interface EventTrigger {
    fun source(): Observable<Long>
}