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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class DatastoreListViewModel
@ViewModelInject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

    private val _progressStatus: ObservableField<Boolean> = ObservableField()
    val progressStatus: ObservableField<Boolean> = _progressStatus

    private val _yearData = arrayListOf<String>()
    val yearData: ArrayList<String> = _yearData

    private val _datastoreResponse = MutableLiveData<Event<Resource<DatastoreResponse>>>()
    val datastoreResponse: LiveData<Event<Resource<DatastoreResponse>>> = _datastoreResponse

    private val _datastoreDbData = MutableLiveData<Event<Resource<List<Record>>>>()
    val datastoreDbData: LiveData<Event<Resource<List<Record>>>> = _datastoreDbData

    fun getDatastoreRecordsApi() {
        _progressStatus.set(true)
        _datastoreResponse.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = datastoreRepository.getDatastoreRecords(Constants.Resource.ID)
            _datastoreResponse.value = Event(response)
            insertDatastoreRecordsDb(filterData(response.data?.result?.records))
            _progressStatus.set(false)
        }
    }

    fun insertDatastoreRecordsDb(datastoreRecords: List<Record>?) = GlobalScope.launch {
        datastoreRepository.insertDatastoreRecords(ArrayList(datastoreRecords))
    }

    fun fetchDatastoreRecordsDb() = GlobalScope.launch {
        _progressStatus.set(true)
        val dataItems = datastoreRepository.fetchDatastoreRecords()
        _datastoreDbData.postValue(Event(Resource.success(dataItems)))
        _progressStatus.set(false)
    }

    fun filterData(arrayList: List<Record>?): ArrayList<Record> {
        val yearWiseVolumeData: ArrayList<Record> = arrayListOf()
        _yearData.clear()
        arrayList?.forEach { datastoreEntry->
            val year = datastoreEntry.quarter.substring(0, 4)
            val yearWiseData =
                yearWiseVolumeData.filter { yearWiseFilter -> yearWiseFilter.year == year }
            if (yearWiseData.isEmpty()) {
                datastoreEntry.year = year
                datastoreEntry.total_volume_data = datastoreEntry.volume_of_mobile_data
                yearWiseVolumeData.add(datastoreEntry)
            } else {
                yearWiseData[0].total_volume_data =
                    yearWiseData[0].total_volume_data.toBigDecimal().plus(datastoreEntry.volume_of_mobile_data.toBigDecimal()).toString()
            }
        }
        yearWiseVolumeData.forEach { yearWiseVolumeDataEach ->
            _yearData.add(yearWiseVolumeDataEach.year)
        }

        return yearWiseVolumeData
    }
}