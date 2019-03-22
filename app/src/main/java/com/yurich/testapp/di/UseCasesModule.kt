package com.yurich.testapp.di

import com.yurich.testapp.domain.choice.CurrentRateChoiceUseCase
import com.yurich.testapp.domain.choice.CurrentRateChoiceUseCaseImpl
import com.yurich.testapp.domain.rates.RatesUseCase
import com.yurich.testapp.domain.rates.RatesUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UseCasesModule {

    @Binds
    @Singleton
    abstract fun bindCurrentRateChoice(useCase: CurrentRateChoiceUseCaseImpl): CurrentRateChoiceUseCase

    @Binds
    @Singleton
    abstract fun bindRatesUseCase(useCase: RatesUseCaseImpl): RatesUseCase

}