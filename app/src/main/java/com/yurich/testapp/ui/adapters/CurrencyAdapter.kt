package com.yurich.testapp.ui.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yurich.testapp.R
import com.yurich.testapp.domain.Currency
import kotlinx.android.synthetic.main.currency.view.*

class CurrencyAdapter(val listener: CurrencyInteractionListener) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    val currencies = mutableListOf<Currency>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency, parent, false)
        val viewHolder = CurrencyViewHolder(view)

        view.setOnClickListener {
            val position = viewHolder.adapterPosition

            // position != NO_POSITION statement is omitted because
            // it is true if position is among currencies.indices
            if (position in currencies.indices) {
                currencies.removeAt(position).also {
                    currencies.add(0, it)
                }
                notifyItemMoved(position, 0)
                if (view.parent is RecyclerView) {
                    (view.parent as RecyclerView).layoutManager?.scrollToPosition(0)
                }
            }
        }

        view.currency_value.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val position = viewHolder.adapterPosition
                if (position in currencies.indices && view.currency_value.isFocused) {
                    val currency = currencies[position]
                    listener.onCurrencyChanged(currency.code, s.toString().replace(",", ".").toDoubleOrNull())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        return viewHolder
    }

    override fun getItemId(position: Int): Long {
        return currencies[position].code.hashCode().toLong()
    }

    override fun getItemCount() = currencies.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        if (position in currencies.indices) {
            holder.bind(currencies[position])
        }
    }

    fun addCurrencies(newCurrencies: Collection<Currency>) {
        currencies.clear()
        currencies.addAll(newCurrencies)
        notifyDataSetChanged()
    }

    fun updateCurrencies(newCurrencies: Map<String, Currency>) {
        if (currencies.isEmpty()) {
            addCurrencies(newCurrencies.values)
            return
        }

        for (i in currencies.indices) {
            val currentCurrencyCode = currencies[i].code
            if (newCurrencies[currentCurrencyCode] != null) {
                currencies[i] = newCurrencies.getValue(currentCurrencyCode)
            }
        }
        notifyDataSetChanged()
    }

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val currencyTitle = itemView.currency_title
        val currencyValue = itemView.currency_value

        fun bind(currency: Currency) {
            currencyTitle.text = currency.code
            if (!currencyValue.isFocused) {
                currencyValue.setText(currencyValue.context.getString(R.string.price_format, currency.cost))
            }
        }
    }
}