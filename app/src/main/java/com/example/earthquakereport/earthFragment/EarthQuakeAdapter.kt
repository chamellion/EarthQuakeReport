package com.example.earthquakereport.earthFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakereport.databinding.ListEarthquakeViewBinding
import com.example.earthquakereport.domain.EarthQuakeDomain
import com.example.earthquakereport.earthFragment.EarthQuakeAdapter.EarthQuakeViewHolder
import com.example.earthquakereport.earthQuakeNetworkAPI.EarthQuake

class EarthQuakeAdapter(private val onclickListener: OnclickListener) :
    ListAdapter<EarthQuakeDomain, EarthQuakeViewHolder>(DiffUtility()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthQuakeViewHolder {
        return EarthQuakeViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: EarthQuakeViewHolder, position: Int) {
        val itemId = getItem(position)
        holder.bind(onclickListener, itemId)
    }


    class EarthQuakeViewHolder(private val listBinding: ListEarthquakeViewBinding) :
        RecyclerView.ViewHolder(listBinding.root) {

        fun bind(
            clickListen: OnclickListener,
            earthQuake: EarthQuakeDomain?
        ) {
            listBinding.earthQuakeProperty = earthQuake
            listBinding.onclickListener = clickListen
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

class DiffUtility : DiffUtil.ItemCallback<EarthQuakeDomain>() {
    override fun areItemsTheSame(oldItem: EarthQuakeDomain, newItem: EarthQuakeDomain): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: EarthQuakeDomain, newItem: EarthQuakeDomain): Boolean {
        return oldItem == newItem
    }

}

class OnclickListener(val clickListener: (url: String) -> Unit) {
    fun onclick(earthQuake: EarthQuakeDomain) {
        clickListener(earthQuake.url)
    }
}
