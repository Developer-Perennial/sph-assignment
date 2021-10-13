package co.test.sphtestapp.view

import androidx.databinding.ObservableField
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

class YearDetailsViewModel : ViewModel() {
    private val _progressStatus: ObservableField<Boolean> = ObservableField()
    val progressStatus: ObservableField<Boolean> = _progressStatus

    private val _quarterWiseData = MutableLiveData<Event<Resource<List<Record>>>>()
    val quarterWiseData: LiveData<Event<Resource<List<Record>>>> = _quarterWiseData

    fun filterData(year: String, arrayList: List<Record>) {
        _progressStatus.set(true)
        _quarterWiseData.value = Event(Resource.loading(null))
        _quarterWiseData.value = Event(Resource.success(arrayList.filter { it.quarter.substring(0, 4) == year }))
        _progressStatus.set(false)
    }
}