package com.sfjava.dkquakes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.sfjava.dkquakes.data.Earthquake
import com.sfjava.dkquakes.service.EarthquakesService
import com.sfjava.dkquakes.util.MainCoroutineRule
import com.sfjava.dkquakes.util.getOrAwaitValue
import com.sfjava.dkquakes.util.observeForTesting
import com.sfjava.dkquakes.viewmodels.EarthquakesListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EarthquakesListViewModelTest {

    // subject under test
    private lateinit var viewModel: EarthquakesListViewModel

    // this will be a "mock" repository to be injected into the viewmodel (see below)
    private lateinit var repository: EarthquakesService

    // set the main coroutines dispatcher for unit testing
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // execute each task synchronously using Architecture Components
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = MockEarthquakesService(
            listOf(
                Earthquake("eq1", "us", 8.0, 100.1, 39.7392, 104.9903, "2011-03-11 04:46:23"),
                Earthquake("eq2", "us", 4.0, 10.1, 30.0, 100.0, "2012-04-11 06:46:23"),
                Earthquake("eq3", "us", 8.0, 100.1, 10.0, 20.0, "2014-05-11 10:46:23")
            )
        )
    }

    @Test
    fun `given list view model when initialized then loading indicator and data observables updated`() {
        // pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // NOTE: data for 'earthquakes' list fetched from repo by view-model [init], so create here
        viewModel = EarthquakesListViewModel(repository, SavedStateHandle())

        // and observe the items to keep LiveData emitting
        viewModel.earthquakes.observeForTesting {

            // Then progress indicator is shown
            assertTrue(viewModel.isLoading.getOrAwaitValue())

            // Execute pending coroutines actions
            mainCoroutineRule.resumeDispatcher()

            // Then progress indicator is hidden
            assertFalse(viewModel.isLoading.getOrAwaitValue())

            // And data correctly loaded
            assertTrue(viewModel.earthquakes.getOrAwaitValue().size == 3)
        }
    }
}