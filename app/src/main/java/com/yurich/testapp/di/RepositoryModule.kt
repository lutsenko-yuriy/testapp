package com.yurich.testapp.di

import com.yurich.testapp.data.RatesRepository
import com.yurich.testapp.data.RatesRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repository: RatesRepositoryImpl): RatesRepository
}