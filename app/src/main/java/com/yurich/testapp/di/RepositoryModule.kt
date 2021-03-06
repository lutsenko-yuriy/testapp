package com.yurich.testapp.di

import com.yurich.testapp.data.RatesRepository
import com.yurich.testapp.data.RatesRepositoryImpl
import com.yurich.testapp.domain.rates.trigger.EventTrigger
import com.yurich.testapp.domain.rates.trigger.EverySecondEventTrigger
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRatesRepository(repository: RatesRepositoryImpl): RatesRepository

    @Binds
    @Singleton
    abstract fun bindEventTrigger(eventTrigger: EverySecondEventTrigger): EventTrigger

}