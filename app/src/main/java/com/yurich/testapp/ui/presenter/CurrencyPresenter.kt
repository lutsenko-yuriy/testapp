package com.yurich.testapp.ui.presenter

import com.yurich.testapp.domain.RatesUseCase
import com.yurich.testapp.ui.adapters.CurrencyInteractionListener
import com.yurich.testapp.ui.view.CurrencyView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyPresenter @Inject constructor(
        private val ratesUseCase: RatesUseCase
) : CurrencyInteractionListener {

    private var view: CurrencyView? = null

    private var ratesInfoDisposable: Disposable? = null

    fun attachView(newView: CurrencyView) {
        if (view == null) {
            view = newView
        }
        if (ratesInfoDisposable?.isDisposed != false) {
            ratesInfoDisposable = ratesUseCase.ratesObservable()
                    .observeOn(Schedulers.computation())
                    .map { currencyMap ->
                        currencyMap.mapValues { Currency(it.key, it.value) }
                    }
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

    override fun onCurrencyChanged(currencyCode: String, amount: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCurrencySelected(currencyCode: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}