package com.ratushny.poeasisto.league

import com.ratushny.poeasisto.league.model.LeagueList
import com.ratushny.poeasisto.league.network.LeagueService
import javax.inject.Inject

interface LeagueListRepository {
    suspend fun getLeagueList(): LeagueList
}

class LeagueListRepositoryImpl @Inject constructor(
    private val service: LeagueService
) :
    LeagueListRepository {
    override suspend fun getLeagueList(): LeagueList {
        return service.getLeagueService(true)
    }
}