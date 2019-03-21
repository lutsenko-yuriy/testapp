package com.yurich.testapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.yurich.testapp.R
import com.yurich.testapp.app.CurrencyApp
import com.yurich.testapp.ui.adapters.CurrencyAdapter
import com.yurich.testapp.domain.Currency
import com.yurich.testapp.ui.presenter.CurrencyPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CurrencyView {

    @Inject
    lateinit var presenter: CurrencyPresenter

    lateinit var adapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as CurrencyApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list_of_currencies.layoutManager = LinearLayoutManager(this, VERTICAL, false)

        adapter = CurrencyAdapter(presenter)
        adapter.setHasStableIds(true)
        list_of_currencies.adapter = this.adapter
        list_of_currencies.setHasFixedSize(true)

        savedInstanceState?.let { bundle ->
            adapter.addCurrencies(bundle.getParcelableArray(CURRENT_CURRENCIES)?.map { it as Currency } ?: emptyList())
        }

        presenter.attachView(this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelableArray(CURRENT_CURRENCIES, adapter.currencies.toTypedArray())
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun displayCurrencies(newCurrencies: Map<String, Currency>) {
        adapter.updateCurrencies(newCurrencies)
    }

    companion object {
        const val CURRENT_CURRENCIES = "currentCurrencies"
    }
}
