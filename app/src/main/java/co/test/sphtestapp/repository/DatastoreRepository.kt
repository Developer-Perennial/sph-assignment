package co.test.sphtestapp.repository

import co.test.sphtestapp.data.network.DataStoreClient
import co.test.sphtestapp.data.network.Resource
import co.test.sphtestapp.data.network.response.DatastoreResponse
import javax.inject.Inject

class DatastoreRepository @Inject constructor(
    private val dataStoreClient: DataStoreClient
) {

    suspend fun getDatastoreRecords(resourceId: String): Resource<DatastoreResponse> {
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
}














