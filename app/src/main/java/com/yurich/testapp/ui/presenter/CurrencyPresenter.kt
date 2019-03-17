package com.yurich.testapp.ui.presenter

import com.yurich.testapp.domain.CurrencyInteractor
import com.yurich.testapp.ui.view.CurrencyView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyPresenter @Inject constructor(val interactor: CurrencyInteractor) {

    var view: CurrencyView? = null

    fun attachView(newView: CurrencyView) {
        if (view == null) {
            view = newView
        }
    }

    fun detachView() {
        if (view != null) {
            view = null
        }
    }
}