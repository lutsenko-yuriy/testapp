package com.yurich.testapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yurich.testapp.ui.presenter.CurrencyPresenter
import com.yurich.testapp.R
import com.yurich.testapp.app.CurrencyApp
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CurrencyView {

    @Inject
    lateinit var presenter: CurrencyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as CurrencyApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)
    }


    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun displayCurrencies() {

    }
}
