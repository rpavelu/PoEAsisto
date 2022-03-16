package com.ratushny.poeasisto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.elevation.SurfaceColors
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.SectionDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.ratushny.poeasisto.ninja.DrawerInterface
import com.ratushny.poeasisto.ninja.ui.NinjaFragment

import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    private lateinit var drawerInterface: DrawerInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val currencyDrawerItem = SecondaryDrawerItem().apply {
            identifier = 0
            nameRes = R.string.currency
        }

        val fragmentDrawerItem = SecondaryDrawerItem().apply {
            identifier = 1
            nameRes = R.string.fragments
        }

        val divCardsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 2
            nameRes = R.string.divination_cards
        }

        val artifactsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 3
            nameRes = R.string.artifacts
        }

        val propheciesDrawerItem = SecondaryDrawerItem().apply {
            identifier = 4
            nameRes = R.string.prophecies
        }

        val oilsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 5
            nameRes = R.string.oils
        }

        val incubatorsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 6
            nameRes = R.string.incubators
        }

        val equipmentAndGemsSection = SectionDrawerItem().apply {
            identifier = 7
            nameRes = R.string.equipmentAndGems
        }

        val uniqueWeaponDrawerItem = SecondaryDrawerItem().apply {
            identifier = 8
            nameRes = R.string.unique_weapons
        }

        val uniqueArmourDrawerItem = SecondaryDrawerItem().apply {
            identifier = 9
            nameRes = R.string.unique_armours
        }

        val uniqueAccessoriesDrawerItem = SecondaryDrawerItem().apply {
            identifier = 10
            nameRes = R.string.unique_accessories
        }

        val uniqueFlasksDrawerItem = SecondaryDrawerItem().apply {
            identifier = 11
            nameRes = R.string.unique_flasks
        }

        val uniqueJewelsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 12
            nameRes = R.string.unique_jewels
        }

        val skillGemsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 13
            nameRes = R.string.skill_gems
        }

        val clusterJewelsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 14
            nameRes = R.string.cluster_jewels
        }

        val atlasSection = SectionDrawerItem().apply {
            identifier = 15
            nameRes = R.string.atlas
        }

        val mapsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 16
            nameRes = R.string.maps
        }

        val blightedMapsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 17
            nameRes = R.string.blighted_maps
        }

        val blightRavagedMapsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 18
            nameRes = R.string.blight_ravaged_maps
        }

        val scourgedMapsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 19
            nameRes = R.string.scourged_maps
        }

        val uniqueMapsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 20
            nameRes = R.string.unique_maps
        }

        val deliriumOrbsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 21
            nameRes = R.string.delirium_orbs
        }

        val invitationsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 22
            nameRes = R.string.invitations
        }

        val scarabsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 23
            nameRes = R.string.scarabs
        }

        val watchstonesDrawerItem = SecondaryDrawerItem().apply {
            identifier = 24
            nameRes = R.string.watchstones
        }

        val craftingSection = SectionDrawerItem().apply {
            identifier = 25
            nameRes = R.string.crafting
        }

        val baseTypesDrawerItem = SecondaryDrawerItem().apply {
            identifier = 26
            nameRes = R.string.base_types
        }

        val fossilsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 27
            nameRes = R.string.fossils
        }

        val resonatorsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 28
            nameRes = R.string.resonators
        }

        val helmetEnchantsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 29
            nameRes = R.string.helmet_enchants
        }

        val beastsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 30
            nameRes = R.string.beasts
        }

        val essencesDrawerItem = SecondaryDrawerItem().apply {
            identifier = 31
            nameRes = R.string.essences
        }

        val vialsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 32
            nameRes = R.string.vials
        }

        slider.itemAdapter.add(
            currencyDrawerItem,
            fragmentDrawerItem,
            divCardsDrawerItem,
            artifactsDrawerItem,
            propheciesDrawerItem,
            oilsDrawerItem,
            incubatorsDrawerItem,
            equipmentAndGemsSection,
            uniqueWeaponDrawerItem,
            uniqueArmourDrawerItem,
            uniqueAccessoriesDrawerItem,
            uniqueFlasksDrawerItem,
            uniqueJewelsDrawerItem,
            skillGemsDrawerItem,
            clusterJewelsDrawerItem,
            atlasSection,
            mapsDrawerItem,
            blightedMapsDrawerItem,
            blightRavagedMapsDrawerItem,
            scourgedMapsDrawerItem,
            uniqueMapsDrawerItem,
            deliriumOrbsDrawerItem,
            invitationsDrawerItem,
            scarabsDrawerItem,
            watchstonesDrawerItem,
            craftingSection,
            baseTypesDrawerItem,
            fossilsDrawerItem,
            resonatorsDrawerItem,
            helmetEnchantsDrawerItem,
            beastsDrawerItem,
            essencesDrawerItem,
            vialsDrawerItem
        )
        slider.setSelection(0)
        slider.onDrawerItemClickListener = { v, drawerItem, position ->
            when (drawerItem) {
                currencyDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.currency)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.CURRENCY)
                }
                fragmentDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.fragments)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.FRAGMENT)
                }
                divCardsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.divination_cards)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.DIVINATION_CARD)
                }
                artifactsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.artifacts)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.ARTIFACT)
                }
                propheciesDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.prophecies)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.PROPHECY)
                }
                oilsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.oils)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.OIL)
                }
                incubatorsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.incubators)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.INCUBATOR)
                }
                uniqueWeaponDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.unique_weapons)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.UNIQUE_WEAPON)
                }
                uniqueArmourDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.unique_armours)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.UNIQUE_ARMOUR)
                }
                uniqueAccessoriesDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.unique_accessories)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.UNIQUE_ACCESSORY)
                }
                uniqueFlasksDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.unique_flasks)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.UNIQUE_FLASK)
                }
                uniqueJewelsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.unique_jewels)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.UNIQUE_JEWEL)
                }
                skillGemsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.skill_gems)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.SKILL_GEM)
                }
                clusterJewelsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.cluster_jewels)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.CLUSTER_JEWEL)
                }
                mapsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.maps)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.MAP)
                }
                blightedMapsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.blighted_maps)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.BLIGHTED_MAP)
                }
                blightRavagedMapsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.blight_ravaged_maps)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.BLIGHT_RAVAGED_MAP)
                }
                scourgedMapsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.scourged_maps)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.SCOURGED_MAP)
                }
                uniqueMapsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.unique_maps)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.UNIQUE_MAP)
                }
                deliriumOrbsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.delirium_orbs)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.DELIRIUM_ORB)
                }
                invitationsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.invitations)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.INVITATION)
                }
                scarabsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.scarabs)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.SCARAB)
                }
                watchstonesDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.watchstones)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.WATCHSTONE)
                }
                baseTypesDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.base_types)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.BASE_TYPE)
                }
                fossilsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.fossils)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.FOSSIL)
                }
                resonatorsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.resonators)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.RESONATOR)
                }
                helmetEnchantsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.helmet_enchants)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.HELMET_ENCHANT)
                }
                beastsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.beasts)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.BEAST)
                }
                essencesDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.essences)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.ESSENCE)
                }
                vialsDrawerItem -> {
                    supportActionBar?.title = resources.getString(R.string.vials)
                    drawerInterface.onDrawerItemClicked(NinjaFragment.VIAL)
                }
            }
            false
        }

        val actionBarColor = SurfaceColors.SURFACE_2.getColor(this)
        window.statusBarColor = actionBarColor
        window.navigationBarColor = actionBarColor

        //Prevent the drawer from being swiped anywhere other than the startDestination
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == nc.graph.startDestinationId) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    fun setDrawerListener(drawerInterface: DrawerInterface) {
        this.drawerInterface = drawerInterface
    }
}
