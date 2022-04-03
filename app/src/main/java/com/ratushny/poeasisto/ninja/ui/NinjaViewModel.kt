package com.ratushny.poeasisto.ninja.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ratushny.poeasisto.ninja.data.NinjaListRepository
import com.ratushny.poeasisto.ninja.data.model.NinjaListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NinjaViewModel @Inject constructor(
    private val ninjaListRepository: NinjaListRepository
) : ViewModel(), CoroutineScope {

    private val _ninjaList = MutableLiveData<List<NinjaListItem>>()
    val ninjaList: LiveData<List<NinjaListItem>>
        get() = _ninjaList

    private val viewModelJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + viewModelJob

    override fun onCleared() {
        viewModelJob.cancel()
    }

    fun getCurrencyData(league: String, currencyType: String) {
        launch {
            _ninjaList.value = ninjaListRepository.getNinjaCurrencyListData(league, currencyType)
        }
    }

    fun getItemData(league: String, itemType: String) {
        launch {
            _ninjaList.value = ninjaListRepository.getNinjaItemListData(league, itemType)
        }
    }
}
