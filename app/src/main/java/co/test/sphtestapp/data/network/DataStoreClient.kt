package co.test.sphtestapp.data.network

import co.test.sphtestapp.data.network.response.DatastoreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DataStoreClient {

    @GET("api/action/datastore_search")
    suspend fun apiGetDatastoreRecords(
        @Query("resource_id") resourceId: String
    ): Response<DatastoreResponse>
}