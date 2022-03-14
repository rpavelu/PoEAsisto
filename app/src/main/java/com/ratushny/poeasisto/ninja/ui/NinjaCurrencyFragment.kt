package com.ratushny.poeasisto.ninja.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.ratushny.poeasisto.R
import com.ratushny.poeasisto.databinding.NinjaCurrencyFragmentBinding
import com.ratushny.poeasisto.fragments.SettingsFragment
import com.ratushny.poeasisto.ninja.data.NinjaCurrencyListRepositoryImpl
import com.ratushny.poeasisto.ninja.data.NinjaListAdapter
import com.ratushny.poeasisto.ninja.data.NinjaNetworkConverterImpl

@Suppress("UNCHECKED_CAST")
class NinjaCurrencyFragment : Fragment() {

    private lateinit var viewModel: NinjaCurrencyViewModel
    private lateinit var binding: NinjaCurrencyFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = NinjaCurrencyFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return NinjaCurrencyViewModel(
                    NinjaCurrencyListRepositoryImpl(
                        NinjaNetworkConverterImpl()
                    )
                ) as T
            }
        })[NinjaCurrencyViewModel::class.java]

        binding.lifecycleOwner = this

        val settingsPrefs = context?.getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
        val leagueName =
            settingsPrefs?.getString(SettingsFragment.PICKED_LEAGUE_PREF_KEY, "Standard")

        viewModel.getData(leagueName.toString())

        val adapter = NinjaListAdapter()
        binding.listScreenRecyclerview.adapter = adapter

        viewModel.ninjaCurrencyList.observe(viewLifecycleOwner) {
            adapter.addCurrencyList(it)
        }

        binding.fab.setOnClickListener {
            viewModel.getData(leagueName.toString())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}
