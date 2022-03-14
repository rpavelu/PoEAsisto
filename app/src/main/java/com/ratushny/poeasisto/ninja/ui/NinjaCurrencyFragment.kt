package com.ratushny.poeasisto.ninja.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.ratushny.poeasisto.MainActivity
import com.ratushny.poeasisto.R
import com.ratushny.poeasisto.databinding.NinjaCurrencyFragmentBinding
import com.ratushny.poeasisto.fragments.SettingsFragment
import com.ratushny.poeasisto.ninja.DrawerInterface
import com.ratushny.poeasisto.ninja.data.NinjaCurrencyListRepositoryImpl
import com.ratushny.poeasisto.ninja.data.NinjaListAdapter
import com.ratushny.poeasisto.ninja.data.NinjaNetworkConverterImpl
import timber.log.Timber

@Suppress("UNCHECKED_CAST")
class NinjaCurrencyFragment : Fragment(), DrawerInterface {

    private lateinit var viewModel: NinjaCurrencyViewModel
    private lateinit var binding: NinjaCurrencyFragmentBinding

    private var league = "Standard" // Default is 'Standard'
    private var currencyType = "Currency" // Default is 'Currency'

    companion object {
        const val CURRENCY = "Currency"
        const val FRAGMENT = "Fragment"
    }

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

        // Set ActionBar title when coming back from other screens
        val actionBar = (activity as MainActivity).supportActionBar
        when (currencyType) {
            CURRENCY -> actionBar?.title = resources.getString(R.string.currency)
            FRAGMENT -> actionBar?.title = resources.getString(R.string.fragments)
        }

        val settingsPrefs = context?.getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
        league =
            settingsPrefs?.getString(SettingsFragment.PICKED_LEAGUE_PREF_KEY, "Standard").toString()

        viewModel.getData(league, currencyType)

        val adapter = NinjaListAdapter()
        binding.listScreenRecyclerview.adapter = adapter

        viewModel.ninjaCurrencyList.observe(viewLifecycleOwner) {
            adapter.addCurrencyList(it)
        }

        binding.fab.setOnClickListener {
            viewModel.getData(league, currencyType)
        }

        setHasOptionsMenu(true)

        // Implement DrawerListener interface
        (activity as MainActivity).setDrawerListener(this)

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

    override fun onCurrencyClicked() {
        currencyType = CURRENCY
        Timber.i("Clicked currency: %s:%s", league, currencyType)
        viewModel.getData(league, currencyType)
    }

    override fun onFragmentClicked() {
        currencyType = FRAGMENT
        Timber.i("Clicked fragment: %s:%s", league, currencyType)
        viewModel.getData(league, currencyType)
    }
}
