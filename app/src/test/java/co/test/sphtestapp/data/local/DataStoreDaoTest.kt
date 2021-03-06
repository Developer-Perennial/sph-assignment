package co.test.sphtestapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.test.sphtestapp.data.network.response.Record
import org.hamcrest.core.IsNull
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DataStoreDaoTest{

    private val dataStoreAmountDao = Mockito.mock(DatastoreDao::class.java)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun fetchDataFromDataBase() {
        val record = Record(1, "2016-Q1","1.00001")
        val recordsArray = arrayListOf(record)
        Mockito.`when`(dataStoreAmountDao.findDatastoreRecords()).thenReturn(recordsArray)

        Assert.assertThat(recordsArray, IsNull.notNullValue())
    }

    @Test
    fun insertDataInDatabase() {
        val record = Record(1, "2016-Q1","1.00001")
        val successInsert = arrayOf(1L)
        Mockito.`when`(dataStoreAmountDao.insertDatastoreRecords(arrayListOf(record))).thenReturn(successInsert)

        Assert.assertThat(successInsert, IsNull.notNullValue())
    }
}