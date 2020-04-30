package com.example.earthquakereport.earthFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakereport.databinding.ListEarthquakeViewBinding
import com.example.earthquakereport.earthFragment.EarthQuakeAdapter.EarthQuakeViewHolder
import com.example.earthquakereport.earthQuakeNetworkAPI.Properties

class EarthQuakeAdapter : ListAdapter<Properties, EarthQuakeViewHolder>(DiffUtility()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthQuakeViewHolder {
        return EarthQuakeViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: EarthQuakeViewHolder, position: Int) {
        val itemId = getItem(position)
        holder.bind(itemId)
    }


    class EarthQuakeViewHolder(private val listBinding: ListEarthquakeViewBinding) :
        RecyclerView.ViewHolder(listBinding.root) {

        fun bind(properties: Properties?){
            listBinding.earthQuakeProperty = properties
            listBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): EarthQuakeViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = ListEarthquakeViewBinding.inflate(inflater, parent, false)
                return EarthQuakeViewHolder(view)
            }
        }
    }
}

class DiffUtility : DiffUtil.ItemCallback<Properties>() {
    override fun areItemsTheSame(oldItem: Properties, newItem: Properties): Boolean {
        return oldItem.ids == newItem.ids
    }

    override fun areContentsTheSame(oldItem: Properties, newItem: Properties): Boolean {
        return oldItem == newItem
    }

}
