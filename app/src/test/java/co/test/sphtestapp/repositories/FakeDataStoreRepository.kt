package co.test.sphtestapp.repositories

import androidx.lifecycle.MutableLiveData
import co.test.sphtestapp.data.network.Resource
import co.test.sphtestapp.data.network.response.*
import co.test.sphtestapp.repository.DataStoreRepository

class FakeDataStoreRepository:DataStoreRepository{

    private val dataUsageItems = mutableListOf<Record>()

    private val observableDataUsageItems = MutableLiveData<List<Record>>(dataUsageItems)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableDataUsageItems.postValue(dataUsageItems)
    }


    override suspend fun insertDatastoreRecords(data: ArrayList<Record>) {
        dataUsageItems.addAll(data)
        refreshLiveData()
    }

    override suspend fun fetchDatastoreRecords(): List<Record> {
        observableDataUsageItems.value = dataUsageItems
        return dataUsageItems
    }

    override suspend fun getDatastoreRecords(resourceId: String): Resource<DatastoreResponse> {
        return if(shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            val field = Field("_id", "int4" )
            val links = Links("/api/action/datastore_search?offset=10&limit=10&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f", " /api/action/datastore_search?limit=10&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f")
            val record = Record(1, "2004-Q3", "0.000384")
            val result = Result(links, arrayListOf(field),10, arrayListOf(record), "a807b7ab-6cad-4aa6-87d0-e283a7353a0f",59 )
            val datastoreResponse = DatastoreResponse("https://data.gov.sg/api/3/action/help_show?name=datastore_search", result, true)
            Resource.success(datastoreResponse)
        }
    }
}











