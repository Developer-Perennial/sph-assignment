package co.test.sphtestapp.data.network.responses

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.test.sphtestapp.data.network.response.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class DataStoreResponseTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testValues() {
        val field = Field("_id", "int4" )
        val links = Links("/api/action/datastore_search?offset=10&limit=10&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f", " /api/action/datastore_search?limit=10&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f")
        val record = Record(1, "2004-Q3", "0.000384","2004","1.5345")
        val result = Result(links, arrayListOf(field),10, arrayListOf(record), "a807b7ab-6cad-4aa6-87d0-e283a7353a0f",59 )
        val datastoreResponse = DatastoreResponse("https://data.gov.sg/api/3/action/help_show?name=datastore_search", result,true)
        assertEquals("https://data.gov.sg/api/3/action/help_show?name=datastore_search", datastoreResponse.help)
        assertEquals(true, datastoreResponse.success)
        assertEquals(1, datastoreResponse.result.records.size)
        assertEquals(1, datastoreResponse.result.records[0]._id)
        assertEquals("0.000384", datastoreResponse.result.records[0].volume_of_mobile_data)
        assertEquals("2004-Q3", datastoreResponse.result.records[0].quarter)
        assertEquals("2004", datastoreResponse.result.records[0].year)
        assertEquals("1.5345", datastoreResponse.result.records[0].total_volume_data)
    }
}