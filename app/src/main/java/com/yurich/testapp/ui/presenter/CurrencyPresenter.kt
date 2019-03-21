package com.yurich.testapp.ui.presenter

import com.yurich.testapp.domain.Currency
import com.yurich.testapp.domain.choice.CurrentRateChoiceUseCase
import com.yurich.testapp.domain.rates.RatesUseCase
import com.yurich.testapp.ui.adapters.CurrencyInteractionListener
import com.yurich.testapp.ui.view.CurrencyView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyPresenter @Inject constructor(
        private val ratesUseCase: RatesUseCase,
        private val currentChoice: CurrentRateChoiceUseCase
) : CurrencyInteractionListener {

    private var view: CurrencyView? = null

    private var ratesInfoDisposable: Disposable? = null

    fun attachView(newView: CurrencyView) {
        if (view == null) {
            view = newView
        }
        if (ratesInfoDisposable?.isDisposed != false) {
            ratesInfoDisposable =
                    Observables.combineLatest(
                            currentChoice.lastCurrencyInput(),
                            ratesUseCase.ratesObservable()
                    ) { currency: Currency, rates: Map<String, Double> ->
                        rates.mapValues { rate ->
                            val newCost = rates[currency.code]?.let { secondRate ->
                                currency.cost?.times(rate.value)?.div(secondRate)
                            }
                            Currency(rate.key, newCost)
                        }
                    }
                    .subscribeOn(Schedulers.computation())
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
        currentChoice.setNextInput(Currency(currencyCode, amount))
    }

}