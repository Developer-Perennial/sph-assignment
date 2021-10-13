package co.test.sphtestapp.data.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.test.sphtestapp.data.network.response.DatastoreResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class DataStoreClientTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: DataStoreClient

    private lateinit var mockWebServer: MockWebServer

    private val resourceId = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f"

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataStoreClient::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getData() {
        enqueueResponse("MockedResponse.json")
        var responseMock: DatastoreResponse? = null
        runBlocking {
            responseMock = service.apiGetDatastoreRecords(resourceId).body() as DatastoreResponse
        }

        val request = mockWebServer.takeRequest()
        assertEquals(
            "/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f",
            request.path
        )

        assertEquals(true, responseMock?.success)
        assertEquals("a807b7ab-6cad-4aa6-87d0-e283a7353a0f", responseMock?.result?.resource_id)
        assertEquals("2004-Q3", responseMock?.result?.records?.get(0)?.quarter)
        assertEquals(10, responseMock?.result?.records?.size)
        assertEquals(1, responseMock?.result?.records?.get(0)?._id)
        assertEquals("0.000384", responseMock?.result?.records?.get(0)?.volume_of_mobile_data)
        assertEquals(3, responseMock?.result?.fields?.size)
        assertEquals("int4", responseMock?.result?.fields?.get(0)?.type)
        assertEquals("_id", responseMock?.result?.fields?.get(0)?.id)
    }

    @Test
    fun getAPITest() {
        enqueueResponse("MockedResponse.json")
        var repos: DatastoreResponse? = null
        runBlocking {
            repos = service.apiGetDatastoreRecords(resourceId).body() as DatastoreResponse
        }

        val request = mockWebServer.takeRequest()
        assertEquals(
            "/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f",
            request.path
        )
        assertEquals(10, repos?.result?.records?.size)
        val repo = repos?.result?.records?.get(0)
        assertEquals(1, repo?._id)
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }

    private suspend fun getAPIData(): DatastoreResponse? {
        return service.apiGetDatastoreRecords(resourceId).body()
    }
}
