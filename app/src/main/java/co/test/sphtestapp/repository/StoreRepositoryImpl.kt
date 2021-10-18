package co.test.sphtestapp.repository

import co.test.sphtestapp.data.local.DatastoreDao
import co.test.sphtestapp.data.network.DataStoreClient
import co.test.sphtestapp.data.network.Resource
import co.test.sphtestapp.data.network.response.DatastoreResponse
import co.test.sphtestapp.data.network.response.Record
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val dataStoreClient: DataStoreClient,
    private val datastoreDao: DatastoreDao
):DataStoreRepository {
    override suspend fun getDatastoreRecords(resourceId: String): Resource<DatastoreResponse> {
        return try {
            val response = dataStoreClient.apiGetDatastoreRecords(resourceId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun insertDatastoreRecords(data: ArrayList<Record>) {
        datastoreDao.insertDatastoreRecords(data)
    }

    override suspend fun fetchDatastoreRecords(): List<Record> {
        return datastoreDao.findDatastoreRecords()
    }

    fun fetchDatastoreYearRecords(year: String): List<Record> {
        return datastoreDao.findDatastoreYearRecords(year)
    }

}














