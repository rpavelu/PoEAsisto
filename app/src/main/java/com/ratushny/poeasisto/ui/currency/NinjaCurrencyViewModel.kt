package com.ratushny.poeasisto.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ratushny.poeasisto.data.ninja.NinjaCurrencyListRepository
import com.ratushny.poeasisto.data.ninja.model.NinjaCurrency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NinjaCurrencyViewModel(
    private val ninjaCurrencyListRepository: NinjaCurrencyListRepository
) : ViewModel(), CoroutineScope {

    private val _ninjaCurrencyList = MutableLiveData<List<NinjaCurrency>>()
    val ninjaCurrencyList: LiveData<List<NinjaCurrency>>
        get() = _ninjaCurrencyList

    private val viewModelJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + viewModelJob

    override fun onCleared() {
        viewModelJob.cancel()
    }

    fun getData() {
        launch {
            _ninjaCurrencyList.value = ninjaCurrencyListRepository.getNinjaCurrencyListData()
        }
    }
}
