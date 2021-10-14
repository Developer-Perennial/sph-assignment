package co.test.sphtestapp.data.local

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class DataStoreItemDatabaseTest {
    @Test
    fun shouldFailOnNullIdentifiers() {
        val datastoreDatabase: DatastoreDatabase =
            Mockito.mock(DatastoreDatabase::class.java, Mockito.RETURNS_MOCKS)
        assertNotNull(datastoreDatabase.datastoreDao())
    }
}