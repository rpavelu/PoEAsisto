package com.ratushny.poeasisto.ninja.data

import com.ratushny.poeasisto.ninja.data.model.NinjaListItem
import com.ratushny.poeasisto.ninja.network.NinjaNetworkService
import timber.log.Timber

interface NinjaListRepository {
    suspend fun getNinjaCurrencyListData(league: String, currencyType: String): List<NinjaListItem>
    suspend fun getNinjaItemListData(league: String, itemType: String): List<NinjaListItem>
}

class NinjaListRepositoryImpl(
    private val ninjaNetworkConverter: NinjaNetworkConverter,
    private val service: NinjaNetworkService = NinjaNetworkService.create()
) :
    NinjaListRepository {
    override suspend fun getNinjaCurrencyListData(
        league: String,
        currencyType: String
    ): List<NinjaListItem> {
        Timber.i("Start loading currency data for %s league", league)
        return ninjaNetworkConverter.convertCurrencyList(
            service.getNinjaCurrencyDataService(
                league,
                currencyType
            )
        )
    }

    override suspend fun getNinjaItemListData(
        league: String,
        itemType: String
    ): List<NinjaListItem> {
        Timber.i("Start loading item data for %s league", league)
        return ninjaNetworkConverter.convertItemList(
            service.getNinjaItemDataService(
                league,
                itemType
            )
        )
    }
}