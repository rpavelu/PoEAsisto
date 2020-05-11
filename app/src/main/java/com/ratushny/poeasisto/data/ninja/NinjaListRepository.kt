package com.ratushny.poeasisto.data.ninja

import android.util.Log
import com.ratushny.poeasisto.data.ninja.currency.model.NinjaCurrency
import com.ratushny.poeasisto.data.ninja.currency.model.network.NinjaCurrencyService

interface NinjaCurrencyListRepository {
    suspend fun getNinjaCurrencyListData(): List<NinjaCurrency>?
}

class NinjaCurrencyListRepositoryImpl(
    private val ninjaNetworkConverter: NinjaNetworkConverter,
    private val service: NinjaCurrencyService = NinjaCurrencyService.create()
) :
    NinjaCurrencyListRepository {
    override suspend fun getNinjaCurrencyListData(): List<NinjaCurrency>? {
        Log.i("Repository", service.getNinjaDataService("Delirium", "Currency").toString())
        return ninjaNetworkConverter.convertCurrencyList(
            service.getNinjaDataService(
                "Delirium",
                "Currency"
            )
        )
    }
}