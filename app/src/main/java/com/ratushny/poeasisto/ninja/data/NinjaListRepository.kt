package com.ratushny.poeasisto.ninja.data

import com.ratushny.poeasisto.ninja.data.currency.model.NinjaCurrency
import com.ratushny.poeasisto.ninja.data.currency.network.NinjaCurrencyService
import timber.log.Timber

interface NinjaCurrencyListRepository {
    suspend fun getNinjaCurrencyListData(league: String): List<NinjaCurrency>
}

class NinjaCurrencyListRepositoryImpl(
    private val ninjaNetworkConverter: NinjaNetworkConverter,
    private val service: NinjaCurrencyService = NinjaCurrencyService.create()
) :
    NinjaCurrencyListRepository {
    override suspend fun getNinjaCurrencyListData(league: String): List<NinjaCurrency> {
        Timber.i("Start loading currency data for %s league", league)
        return ninjaNetworkConverter.convertCurrencyList(
            service.getNinjaDataService(
                league,
                "Currency"
            )
        )
    }
}