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

class YearDetailsViewModel
@ViewModelInject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

//    private val _datastoreYearDbData = MutableLiveData<List<Record>>()
//    val datastoreYearDbData: LiveData<List<Record>> = _datastoreYearDbData
    private var datastoreYearDbData: MutableLiveData<List<Record>> = MutableLiveData()

    fun fetchDatastoreRecordsDb() = GlobalScope.launch {
        datastoreYearDbData.postValue(datastoreRepository.fetchDatastoreRecords())
    }

    fun getDatastoreEntry() = datastoreYearDbData
}