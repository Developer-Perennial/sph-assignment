package co.test.sphtestapp.view

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.test.sphtestapp.common.Constants
import co.test.sphtestapp.common.Event
import co.test.sphtestapp.data.network.Resource
import co.test.sphtestapp.data.network.response.DatastoreResponse
import co.test.sphtestapp.data.network.response.Record
import co.test.sphtestapp.repository.DatastoreRepository
import kotlinx.coroutines.launch

open class RecordYearPagerViewModel
@ViewModelInject constructor() : ViewModel() {

    fun filterData(year: String, arrayList: List<Record>): List<Record> {
        return arrayList.filter { it.year == year }
    }
}