package com.yurich.testapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yurich.testapp.R
import com.yurich.testapp.ui.presenter.Currency
import kotlinx.android.synthetic.main.currency.view.*

class CurrencyAdapter(val listener: CurrencyInteractionListener) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    val currencies = mutableListOf<Currency>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency, parent, false)
        val viewHolder = CurrencyViewHolder(view)

        return viewHolder
    }

    override fun getItemCount() = currencies.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        if (position in currencies.indices) {
            holder.bind(currencies[position])
        }
    }

    fun updateCurrencies(newCurrencies: Map<String, Currency>) {
        if (currencies.size < newCurrencies.size) {
            currencies.clear()
            currencies.addAll(newCurrencies.values)
        } else {
            for (i in currencies.indices) {
                val currentCurrencyCode = currencies[i].code
                if (newCurrencies[currentCurrencyCode] != null) {
                    currencies[i] = newCurrencies.getValue(currentCurrencyCode)
                }
            }
        }
        notifyDataSetChanged()
    }

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var item: Currency

        val currencyTitle = itemView.currency_title
        val currencyValue = itemView.currency_value

        fun bind(currency: Currency) {
            this.item = currency

            currencyTitle.text = currency.code
            currencyValue.setText(currency.cost.toString())
        }
    }
}