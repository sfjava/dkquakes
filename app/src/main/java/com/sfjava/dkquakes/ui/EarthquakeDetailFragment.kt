package com.sfjava.dkquakes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sfjava.dkquakes.databinding.EarthquakeDetailFragmentBinding
import com.sfjava.dkquakes.viewmodels.EarthquakeDetailViewModel

class EarthquakeDetailFragment: Fragment() {

    private val args: EarthquakeDetailFragmentArgs by navArgs()

    private val viewModel: EarthquakeDetailViewModel by viewModels()

    private lateinit var binding: EarthquakeDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EarthquakeDetailFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@EarthquakeDetailFragment
            viewModel = this@EarthquakeDetailFragment.viewModel
        }
        viewModel.loadEarthquakeDetails(args.earthquakeId) // load item details
        return binding.root
    }

}