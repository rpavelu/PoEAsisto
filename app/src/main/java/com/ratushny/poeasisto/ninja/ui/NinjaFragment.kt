package com.ratushny.poeasisto.ninja.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.ratushny.poeasisto.ApplicationComponentHolder
import com.ratushny.poeasisto.MainActivity
import com.ratushny.poeasisto.R
import com.ratushny.poeasisto.databinding.NinjaFragmentBinding
import com.ratushny.poeasisto.fragments.SettingsFragment
import com.ratushny.poeasisto.ninja.DrawerInterface
import com.ratushny.poeasisto.ninja.data.NinjaListAdapter
import kotlinx.android.synthetic.main.ninja_fragment.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class NinjaFragment : Fragment(), DrawerInterface {

    @Inject
    lateinit var viewModelFactory: Provider<NinjaViewModel>

    private lateinit var viewModel: NinjaViewModel
    private lateinit var binding: NinjaFragmentBinding

    private var league = "Standard" // Default is 'Standard'
    private var itemType = CURRENCY // Default is 'Currency'

    companion object {
        const val CURRENCY = "Currency"
        const val FRAGMENT = "Fragment"
        const val DIVINATION_CARD = "DivinationCard"
        const val ARTIFACT = "Artifact"
        const val PROPHECY = "Prophecy"
        const val OIL = "Oil"
        const val INCUBATOR = "Incubator"

        const val UNIQUE_WEAPON = "UniqueWeapon"
        const val UNIQUE_ARMOUR = "UniqueArmour"
        const val UNIQUE_ACCESSORY = "UniqueAccessory"
        const val UNIQUE_FLASK = "UniqueFlask"
        const val UNIQUE_JEWEL = "UniqueJewel"
        const val SKILL_GEM = "SkillGem"
        const val CLUSTER_JEWEL = "ClusterJewel"

        const val MAP = "Map"
        const val BLIGHTED_MAP = "BlightedMap"
        const val BLIGHT_RAVAGED_MAP = "BlightRavagedMap"
        const val SCOURGED_MAP = "ScourgedMap"
        const val UNIQUE_MAP = "UniqueMap"
        const val DELIRIUM_ORB = "DeliriumOrb"
        const val INVITATION = "Invitation"
        const val SCARAB = "Scarab"
        const val WATCHSTONE = "Watchstone"

        const val BASE_TYPE = "BaseType"
        const val FOSSIL = "Fossil"
        const val RESONATOR = "Resonator"
        const val HELMET_ENCHANT = "HelmetEnchant"
        const val BEAST = "Beast"
        const val ESSENCE = "Essence"
        const val VIAL = "Vial"
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as ApplicationComponentHolder)
            .ninjaComponent
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = NinjaFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModelFactory.get() as T
            }
        })[NinjaViewModel::class.java]

        binding.lifecycleOwner = this

        // Set ActionBar title when coming back from other screens
        val actionBar = (activity as MainActivity).supportActionBar
        when (itemType) {
            CURRENCY -> actionBar?.title = resources.getString(R.string.currency)
            FRAGMENT -> actionBar?.title = resources.getString(R.string.fragments)
            DIVINATION_CARD -> actionBar?.title = resources.getString(R.string.divination_cards)
            ARTIFACT -> actionBar?.title = resources.getString(R.string.artifacts)
            PROPHECY -> actionBar?.title = resources.getString(R.string.prophecies)
            OIL -> actionBar?.title = resources.getString(R.string.oils)
            INCUBATOR -> actionBar?.title = resources.getString(R.string.incubators)
            UNIQUE_WEAPON -> actionBar?.title = resources.getString(R.string.unique_weapons)
            UNIQUE_ARMOUR -> actionBar?.title = resources.getString(R.string.unique_armours)
            UNIQUE_ACCESSORY -> actionBar?.title = resources.getString(R.string.unique_accessories)
            UNIQUE_FLASK -> actionBar?.title = resources.getString(R.string.unique_flasks)
            UNIQUE_JEWEL -> actionBar?.title = resources.getString(R.string.unique_jewels)
            SKILL_GEM -> actionBar?.title = resources.getString(R.string.skill_gems)
            CLUSTER_JEWEL -> actionBar?.title = resources.getString(R.string.cluster_jewels)
            MAP -> actionBar?.title = resources.getString(R.string.maps)
            BLIGHTED_MAP -> actionBar?.title = resources.getString(R.string.blighted_maps)
            BLIGHT_RAVAGED_MAP -> actionBar?.title =
                resources.getString(R.string.blight_ravaged_maps)
            SCOURGED_MAP -> actionBar?.title = resources.getString(R.string.scourged_maps)
            UNIQUE_MAP -> actionBar?.title = resources.getString(R.string.unique_maps)
            DELIRIUM_ORB -> actionBar?.title = resources.getString(R.string.delirium_orbs)
            INVITATION -> actionBar?.title = resources.getString(R.string.invitations)
            WATCHSTONE -> actionBar?.title = resources.getString(R.string.watchstones)
            BASE_TYPE -> actionBar?.title = resources.getString(R.string.base_types)
            FOSSIL -> actionBar?.title = resources.getString(R.string.fossils)
            RESONATOR -> actionBar?.title = resources.getString(R.string.resonators)
            HELMET_ENCHANT -> actionBar?.title = resources.getString(R.string.helmet_enchants)
            BEAST -> actionBar?.title = resources.getString(R.string.beasts)
            ESSENCE -> actionBar?.title = resources.getString(R.string.essences)
            VIAL -> actionBar?.title = resources.getString(R.string.vials)
        }

        val settingsPrefs = context?.getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
        league =
            settingsPrefs?.getString(SettingsFragment.PICKED_LEAGUE_PREF_KEY, "Standard").toString()

        // Load data on fragment creating
        loadData()

        val adapter = NinjaListAdapter()
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newString: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newString: String?): Boolean {
                adapter.filter.filter(newString)
                return false
            }
        })

        binding.listScreenRecyclerview.adapter = adapter

        viewModel.ninjaList.observe(viewLifecycleOwner) {
            adapter.addItemList(it)
        }

        binding.fab.setOnClickListener {
            loadData()
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

    override fun onDrawerItemClicked(type: String) {
        itemType = type
        Timber.i("Clicked drawer item and loading: %s:%s", league, type)
        loadData()
    }

    private fun loadData() {
        if (!isInternetAvailable(requireContext())) {
            return
        }

        if (itemType == CURRENCY || itemType == FRAGMENT)
            viewModel.getCurrencyData(league, itemType)
        else viewModel.getItemData(league, itemType)
    }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        if (network_error_text != null) {
            if (result) {
                network_error_text.visibility = View.GONE
            } else network_error_text.visibility = View.VISIBLE
        }

        return result
    }
}
