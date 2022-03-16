package com.ratushny.poeasisto.ninja.data

import com.ratushny.poeasisto.ninja.data.model.NinjaListItem
import com.ratushny.poeasisto.ninja.data.model.currencyoverview.NinjaCurrency
import com.ratushny.poeasisto.ninja.data.model.currencyoverview.NinjaCurrencyResponse
import com.ratushny.poeasisto.ninja.data.model.itemoverview.NinjaItem
import com.ratushny.poeasisto.ninja.data.model.itemoverview.NinjaItemResponse
import timber.log.Timber

interface NinjaNetworkConverter {
    fun convertCurrencyList(response: NinjaCurrencyResponse): List<NinjaListItem>
    fun convertItemList(response: NinjaItemResponse): List<NinjaListItem>
}

class NinjaNetworkConverterImpl : NinjaNetworkConverter {
    override fun convertCurrencyList(response: NinjaCurrencyResponse): List<NinjaListItem> =
        response.lines
            .filter { it.receive != null }
            .map { dtoData ->
                NinjaListItem(
                    dtoData.currencyTypeName,
                    dtoData.receive.value,
                    response.currencyDetails[dtoData.receive.get_currency_id - 1].icon,
                    dtoData.receiveSparkLine.totalChange
                )
            }

    override fun convertItemList(response: NinjaItemResponse): List<NinjaListItem> =
        response.lines
            .map { dtoData ->
                Timber.d("here is %s", dtoData.name)
                NinjaListItem(
                    dtoData.name,
                    dtoData.chaosValue,
                    dtoData.icon,
                    dtoData.sparkline.totalChange
                )
            }
}