package com.ratushny.poeasisto.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ratushny.poeasisto.league.LeagueListRepository
import com.ratushny.poeasisto.league.model.LeagueList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SettingsViewModel @Inject constructor(private val leagueListRepository: LeagueListRepository) : ViewModel(),
    CoroutineScope {

    private val _leagueList = MutableLiveData<LeagueList>()
    val leagueList: LiveData<LeagueList>
        get() = _leagueList

    private val viewModelJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + viewModelJob

    override fun onCleared() {
        viewModelJob.cancel()
    }

    fun getLeagueList() {
        launch {
            _leagueList.value = leagueListRepository.getLeagueList()
            Timber.i("League list loaded in Settings ViewModel: %s", _leagueList.value)
        }
    }
}