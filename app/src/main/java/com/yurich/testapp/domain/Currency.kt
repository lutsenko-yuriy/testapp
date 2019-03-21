package com.yurich.testapp.domain

import android.os.Parcel
import android.os.Parcelable
import com.yurich.testapp.data.RatesRepositoryImpl.Companion.BASE_CURRENCY
import com.yurich.testapp.data.RatesRepositoryImpl.Companion.DEFAULT_RATES

data class Currency(
        val code: String,
        val cost: Double?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readValue(Double::class.java.classLoader) as? Double)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeValue(cost)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Currency> {
        override fun createFromParcel(parcel: Parcel): Currency {
            return Currency(parcel)
        }

        override fun newArray(size: Int): Array<Currency?> {
            return arrayOfNulls(size)
        }

        val DEFAULT_CURRENCY = Currency(BASE_CURRENCY, DEFAULT_RATES)
    }
}