package com.yurich.testapp.app

import android.app.Application
import com.yurich.testapp.di.AppComponent
import com.yurich.testapp.di.DaggerAppComponent

class CurrencyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
                .context(this)
                .build()

        super.onCreate()
    }

}