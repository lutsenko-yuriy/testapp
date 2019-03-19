package com.yurich.testapp.ui.presenter

import com.yurich.testapp.domain.CurrencyInteractor
import com.yurich.testapp.ui.adapters.CurrencyInteractionListener
import com.yurich.testapp.ui.view.CurrencyView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyPresenter @Inject constructor(val interactor: CurrencyInteractor) : CurrencyInteractionListener {

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

    override fun onCurrencyChanged(currencyCode: String, amount: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCurrencySelected(currencyCode: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}