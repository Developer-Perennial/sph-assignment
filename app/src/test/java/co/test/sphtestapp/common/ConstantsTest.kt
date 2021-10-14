package co.test.sphtestapp.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ConstantsTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testValues() {
        assertEquals("datastore_records_db", Constants.Database.DATABASE_NAME)
        assertEquals("https://data.gov.sg/", Constants.Urls.BASE_URL)
        assertEquals("a807b7ab-6cad-4aa6-87d0-e283a7353a0f", Constants.Resource.ID)
        assertEquals("position", Constants.IntentKeys.POSITION)
        assertEquals("year_data", Constants.IntentKeys.YEAR_DATA)
        assertEquals("year_selected", Constants.IntentKeys.YEAR_SELECTED)
    }
}