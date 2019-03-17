package com.yurich.testapp.app

import android.app.Application
import com.yurich.testapp.di.AppComponent

class CurrencyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
    }

}