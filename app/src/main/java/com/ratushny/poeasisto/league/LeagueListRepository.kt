package com.ratushny.poeasisto.league

import android.util.Log
import com.ratushny.poeasisto.league.network.LeagueService
import com.ratushny.poeasisto.ninja.data.NinjaNetworkConverter
import com.ratushny.poeasisto.ninja.data.currency.model.NinjaCurrency
import com.ratushny.poeasisto.ninja.data.currency.network.NinjaCurrencyService

interface LeagueListRepository {
    suspend fun getCurrencyList(): List<NinjaCurrency>
}

class LeagueListRepositoryImpl(
    private val leagueNetworkConverter: NinjaNetworkConverter,
    private val service: NinjaCurrencyService = NinjaCurrencyService.create()
) :
    LeagueListRepository {
    override suspend fun getCurrencyList(): List<NinjaCurrency> {
        // TODO: Change it
        Log.i("Repository", service.getNinjaDataService("Archnemesis", "Currency").toString())
        return leagueNetworkConverter.convertCurrencyList(
            service.getNinjaDataService(
                "Archnemesis",
                "Currency"
            )
        )
    }
}