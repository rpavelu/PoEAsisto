package com.ratushny.poeasisto.ninja.data

import com.ratushny.poeasisto.ninja.data.currency.model.NinjaCurrency
import com.ratushny.poeasisto.ninja.data.currency.model.NinjaCurrencyResponse

interface NinjaNetworkConverter {
    fun convertCurrencyList(response: NinjaCurrencyResponse): List<NinjaCurrency>
}

class NinjaNetworkConverterImpl : NinjaNetworkConverter {
    override fun convertCurrencyList(response: NinjaCurrencyResponse): List<NinjaCurrency> =
        response.lines.map { dtoData ->

            // TODO: This shit is a dirty hack to avoid NPE. I need both price changes.
            var value = 0.0
            var currencyId = 1

            if (dtoData.receive != null) {
                value = dtoData.receive.value
                currencyId = dtoData.receive.get_currency_id
            }

            NinjaCurrency(
                dtoData.currencyTypeName,
                value,
                response.currencyDetails[currencyId - 1].icon,
                dtoData.receiveSparkLine.totalChange
            )
        }
}