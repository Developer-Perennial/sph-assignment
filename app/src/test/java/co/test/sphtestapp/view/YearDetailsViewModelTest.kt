package co.test.sphtestapp.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.test.sphtestapp.MainCoroutineRule
import co.test.sphtestapp.data.network.Status
import co.test.sphtestapp.getOrAwaitValueTest
import co.test.sphtestapp.repositories.FakeDataStoreRepository
import co.test.sphtestapp.viewmodel.YearDetailsViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class YearDetailsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel1: YearDetailsViewModel

    private lateinit var fakeDataStoreRepository1: FakeDataStoreRepository

    @Before
    fun setup() {
        fakeDataStoreRepository1 = FakeDataStoreRepository()
        viewModel1 = YearDetailsViewModel(fakeDataStoreRepository1)
    }

    @Test
    fun `get items`() {
        viewModel1.fetchDatastoreRecordsDb()

        val value = viewModel1.datastoreDbData.getOrAwaitValueTest()

        Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

}