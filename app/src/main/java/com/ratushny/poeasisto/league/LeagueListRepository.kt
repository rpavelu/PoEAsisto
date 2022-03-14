package com.ratushny.poeasisto.league

import com.ratushny.poeasisto.league.model.LeagueList
import com.ratushny.poeasisto.league.network.LeagueService

interface LeagueListRepository {
    suspend fun getLeagueList(): LeagueList
}

class LeagueListRepositoryImpl(
    private val service: LeagueService = LeagueService.create()
) :
    LeagueListRepository {
    override suspend fun getLeagueList(): LeagueList {
        return service.getLeagueService(true)
    }
}