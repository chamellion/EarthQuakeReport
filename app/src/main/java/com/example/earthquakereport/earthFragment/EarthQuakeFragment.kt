package com.example.earthquakereport.earthFragment

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.earthquakereport.R
import com.example.earthquakereport.database.EarthQuakeDatabase
import com.example.earthquakereport.databinding.FragmentEarthQuakeBinding

/**
 * A simple [Fragment] subclass.
 */
class EarthQuakeFragment : Fragment() {

    private lateinit var viewModel: EarthQuakeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentEarthQuakeBinding = FragmentEarthQuakeBinding.inflate(
            inflater,
            container, false
        )
        val application: Application = requireNotNull(activity?.application)
        val database: EarthQuakeDatabase = EarthQuakeDatabase.getInstance(requireContext())
        val factory = EarthQuakeViewModelFactory(database, application)
        viewModel = ViewModelProvider(this, factory).get(EarthQuakeViewModel::class.java)


        binding.earthQuakeViewModel = viewModel
        binding.lifecycleOwner = this


        val adapter = EarthQuakeAdapter(OnclickListener {
            val uri = Uri.parse(it)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            if (intent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(intent)
            }
        })
        binding.recyclerView.adapter = adapter

        viewModel.earthQuake.observe(viewLifecycleOwner, Observer {
            it?.apply {
                adapter.submitList(it)
            }
        })


        setHasOptionsMenu(true)


        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = this.findNavController()
        return NavigationUI.onNavDestinationSelected(item, navController) ||
                super.onOptionsItemSelected(item)
    }

    companion object {
        var refreshLayout = false
    }

}
