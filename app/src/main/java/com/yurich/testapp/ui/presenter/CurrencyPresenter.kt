package com.yurich.testapp.ui.presenter

import com.yurich.testapp.domain.Currency
import com.yurich.testapp.domain.CurrencyInteractor
import com.yurich.testapp.ui.adapters.CurrencyInteractionListener
import com.yurich.testapp.ui.view.CurrencyView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyPresenter @Inject constructor(
        private val interactor: CurrencyInteractor
) : CurrencyInteractionListener {

    private var view: CurrencyView? = null

    private var ratesInfoDisposable: Disposable? = null

    fun attachView(newView: CurrencyView) {
        if (view == null) {
            view = newView
        }
        if (ratesInfoDisposable?.isDisposed != false) {
            ratesInfoDisposable =
                    interactor.pricesObservable()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { view?.displayCurrencies(it) }
        }
    }

    fun detachView() {
        if (view != null) {
            view = null
        }
        if (ratesInfoDisposable?.isDisposed != true) {
            ratesInfoDisposable?.dispose()
        }
    }

    override fun onCurrencyChanged(currencyCode: String, amount: Double?) {
        interactor.setNextInput(Currency(currencyCode, amount))
    }

}