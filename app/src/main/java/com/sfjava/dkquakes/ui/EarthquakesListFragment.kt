package com.sfjava.dkquakes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.sfjava.dkquakes.DKQuakesApplication
import com.sfjava.dkquakes.databinding.EarthquakesListFragmentBinding
import com.sfjava.dkquakes.service.EarthquakesService
import com.sfjava.dkquakes.viewmodels.EarthquakesListViewModel

class EarthquakesListFragment : Fragment() {

    // NOTE: the following could be done by creating a typical extension fun, e.g. [Fragment.getViewModelFactory]
    // but, for now, am lazily getting the Service (repo) below and providing it to the factory's constructor :))
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

    // NOTE: the following is be generic, and could be used for all view-models...
    // TODO: hmm, though when using Dagger we might not need this at all ??
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
                    EarthquakesListViewModel(earthquakesService, handle)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }
}
