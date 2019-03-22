package com.yurich.testapp.di

import android.content.Context
import com.yurich.testapp.ui.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    LocalStorageModule::class,
    RepositoryModule::class,
    UseCasesModule::class
])
interface AppComponent {

    fun context(): Context

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}