package co.test.sphtestapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.test.sphtestapp.data.network.response.Record
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class DataStoreItemTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testStoreItems() {
        val dataStoreItem = Record(1,"2016-Q1", "1.00001")
        assertEquals(1, dataStoreItem._id)
        assertEquals("2016-Q1", dataStoreItem.quarter)
        assertEquals("1.00001", dataStoreItem.volume_of_mobile_data)
    }
}