package com.sfjava.dkquakes.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.sfjava.dkquakes.DKQuakesApplication
import com.sfjava.dkquakes.databinding.EarthquakesListFragmentBinding
import com.sfjava.dkquakes.service.EarthquakesService

class EarthquakesListFragment : Fragment() {

    private val earthquakesService by lazy { (requireContext().applicationContext as DKQuakesApplication).earthquakesService }
    private val viewModel: EarthquakesListViewModel by viewModels() { ViewModelFactory(earthquakesService, this) }
    private lateinit var binding: EarthquakesListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = EarthquakesListFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@EarthquakesListFragment
            viewModel = this@EarthquakesListFragment.viewModel
        }
        binding.earthquakesList.adapter = EarthquakeListAdapter().also {
            subscribeUi(it)
        }
        return binding.root
    }

    private fun subscribeUi(adapter: EarthquakeListAdapter) {
        viewModel.earthquakes.observe(viewLifecycleOwner) { earthquakes ->
            adapter.submitList(earthquakes)
        }
    }

    // FIXME: this can be generic, for all view-models; though using Dagger we might not need this?
    private class ViewModelFactory constructor(
        private val earthquakesService: EarthquakesService,
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null
    ) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ) = with(modelClass) {
            when {
                isAssignableFrom(EarthquakesListViewModel::class.java) ->
                    EarthquakesListViewModel(earthquakesService) //, handle) // FIXME: handle state persistence?
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }
}
