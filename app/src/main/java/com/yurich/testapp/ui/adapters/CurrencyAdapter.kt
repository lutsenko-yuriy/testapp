package com.yurich.testapp.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yurich.testapp.ui.presenter.Currency

class CurrencyAdapter(val listener: CurrencyInteractionListener) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun updateCurrencies(newCurrencies: List<Currency>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}