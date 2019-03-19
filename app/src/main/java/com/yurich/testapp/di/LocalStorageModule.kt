package com.yurich.testapp.di

import com.yurich.testapp.data.cache.RatesCache
import com.yurich.testapp.data.cache.RatesCacheImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class LocalStorageModule {

    @Binds
    @Singleton
    abstract fun bindLocalStorage(cache: RatesCacheImpl): RatesCache

}