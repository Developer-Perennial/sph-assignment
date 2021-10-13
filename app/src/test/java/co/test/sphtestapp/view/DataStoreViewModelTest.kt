package co.test.sphtestapp.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.test.sphtestapp.MainCoroutineRule
import co.test.sphtestapp.data.network.Status
import co.test.sphtestapp.data.network.response.Record
import co.test.sphtestapp.getOrAwaitValueTest
import co.test.sphtestapp.repositories.FakeDataStoreRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DataStoreViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel1: DatastoreListViewModel
    private lateinit var viewModel2: DatastoreListViewModel

    private lateinit var fakeDataStoreRepository1: FakeDataStoreRepository
    private lateinit var fakeDataStoreRepository2: FakeDataStoreRepository

    @Before
    fun setup() {
        fakeDataStoreRepository1 = FakeDataStoreRepository()
        fakeDataStoreRepository2 = FakeDataStoreRepository()
        fakeDataStoreRepository2.setShouldReturnNetworkError(true)
        viewModel1 = DatastoreListViewModel(fakeDataStoreRepository1)
        viewModel2 = DatastoreListViewModel(fakeDataStoreRepository2)
    }

    @Test
    fun `insert single item`() {
        val dataUsageItem = Record(1,"2016-Q1", "1.00001")
        viewModel1.insertDatastoreRecordsDb(arrayListOf(dataUsageItem))

        val value = viewModel1.insertDataStoreItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `fetch items`() {
        viewModel1.fetchDatastoreRecordsDb()

        val value = viewModel1.datastoreDbData.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `fetch items from api`() {
        viewModel1.getDatastoreRecordsApi()

        val value = viewModel1.datastoreResponse.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `fetch items from api failed response`() {
        viewModel2.getDatastoreRecordsApi()

        val value = viewModel2.datastoreResponse.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

}













