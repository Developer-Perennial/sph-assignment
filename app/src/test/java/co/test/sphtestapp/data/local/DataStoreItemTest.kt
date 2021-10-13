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
    fun testValues() {
        val dataUsageItem = Record(1,"2016-Q1", "1.00001")
        assertEquals(1, dataUsageItem._id)
        assertEquals("2016-Q1", dataUsageItem.quarter)
        assertEquals("1.00001", dataUsageItem.volume_of_mobile_data)
    }
}