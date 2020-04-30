package com.example.earthquakereport.earthFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.earthquakereport.databinding.FragmentEarthQuakeBinding

/**
 * A simple [Fragment] subclass.
 */
class EarthQuakeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentEarthQuakeBinding = FragmentEarthQuakeBinding.inflate(inflater,
            container, false)
        val viewModel : EarthQuakeViewModel = ViewModelProvider(this).get(EarthQuakeViewModel::class.java)

        binding.earthQuakeViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = EarthQuakeAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.earthQuakeList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root

    }

}
