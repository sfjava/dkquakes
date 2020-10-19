package com.sfjava.dkquakes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sfjava.dkquakes.data.Earthquake
import com.sfjava.dkquakes.databinding.ListItemEarthquakeBinding
import com.sfjava.dkquakes.viewmodels.EarthquakesListViewModel

/**
 * Adapter for the [RecyclerView] in [EarthquakesListFragment].
 */
class EarthquakeListAdapter(val viewModel: EarthquakesListViewModel) :
    ListAdapter<Earthquake, RecyclerView.ViewHolder>(EarthquakeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EarthquakeViewHolder(
            ListItemEarthquakeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val earthquake = getItem(position)
        (holder as EarthquakeViewHolder).bind(viewModel, earthquake)
    }

    class EarthquakeViewHolder(
        private val binding: ListItemEarthquakeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: EarthquakesListViewModel, item: Earthquake) {
            binding.apply {
                earthquake = item
                viewmodel = viewModel // NOTE: list view-model needed here for click-listener fun
                executePendingBindings()
            }
        }
    }

    private class EarthquakeDiffCallback: DiffUtil.ItemCallback<Earthquake>() {

        override fun areItemsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem == newItem
        }
    }
}
