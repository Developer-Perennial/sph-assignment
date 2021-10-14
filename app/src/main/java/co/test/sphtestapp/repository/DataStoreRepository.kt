package co.test.sphtestapp.repository

import co.test.sphtestapp.data.network.Resource
import co.test.sphtestapp.data.network.response.DatastoreResponse
import co.test.sphtestapp.data.network.response.Record

interface DataStoreRepository {

    suspend fun insertDatastoreRecords(data: ArrayList<Record>)

    suspend fun fetchDatastoreRecords(): List<Record>

    suspend fun getDatastoreRecords(resourceId: String): Resource<DatastoreResponse>
}