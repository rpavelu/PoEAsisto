package com.ratushny.poeasisto.data.ninja

import com.ratushny.poeasisto.data.ninja.model.NinjaCurrency
import com.ratushny.poeasisto.data.ninja.model.NinjaCurrencyResponse

interface NinjaNetworkConverter {
    fun convertCurrencyList(response: NinjaCurrencyResponse): List<NinjaCurrency>?
}

class NinjaNetworkConverterImpl : NinjaNetworkConverter {
    override fun convertCurrencyList(response: NinjaCurrencyResponse): List<NinjaCurrency>? =
        response.lines.map { dtoList ->
            NinjaCurrency(
                dtoList.currencyTypeName,
                dtoList.receive.value,
                response.currencyDetails[dtoList.receive.get_currency_id - 1].icon
            )
        }
}