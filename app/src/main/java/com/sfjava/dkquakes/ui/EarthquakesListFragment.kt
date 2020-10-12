package com.sfjava.dkquakes.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sfjava.dkquakes.databinding.EarthquakesListFragmentBinding

class EarthquakesListFragment : Fragment() {

    private val viewModel: EarthquakesListViewModel by viewModels()
    private lateinit var binding: EarthquakesListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = EarthquakesListFragmentBinding.inflate(inflater, container, false) // .apply {
        //      viewModel = this@EarthquakesListFragment.viewModel
        // }
        context ?: return binding.root

        val adapter = EarthquakeListAdapter()
        binding.earthquakesList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: EarthquakeListAdapter) {
        viewModel.earthquakes.observe(viewLifecycleOwner) { earthquakes ->
            adapter.submitList(earthquakes)
        }
    }
}
