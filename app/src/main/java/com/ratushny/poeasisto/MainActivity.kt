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
import com.mikepenz.materialdrawer.model.interfaces.iconRes
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

        initDrawer()

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

    private fun initDrawer() {
        val currencyDrawerItem = SecondaryDrawerItem().apply {
            identifier = 0
            nameRes = R.string.currency
            iconRes = R.drawable.chaos_pic
        }

        val fragmentDrawerItem = SecondaryDrawerItem().apply {
            identifier = 1
            nameRes = R.string.fragments
            iconRes = R.drawable.framgent_phoenix
        }

        val divCardsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 2
            nameRes = R.string.divination_cards
            iconRes = R.drawable.div_icon
        }

        val artifactsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 3
            nameRes = R.string.artifacts
            iconRes = R.drawable.artifact_icon
        }

        val propheciesDrawerItem = SecondaryDrawerItem().apply {
            identifier = 4
            nameRes = R.string.prophecies
            iconRes = R.drawable.prophecy_icon
        }

        val oilsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 5
            nameRes = R.string.oils
            iconRes = R.drawable.oil_icon
        }

        val incubatorsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 6
            nameRes = R.string.incubators
            iconRes = R.drawable.incubator_icon
        }

        val equipmentAndGemsSection = SectionDrawerItem().apply {
            identifier = 7
            nameRes = R.string.equipmentAndGems
        }

        val uniqueWeaponDrawerItem = SecondaryDrawerItem().apply {
            identifier = 8
            nameRes = R.string.unique_weapons
            iconRes = R.drawable.unique_weapons_icon
        }

        val uniqueArmourDrawerItem = SecondaryDrawerItem().apply {
            identifier = 9
            nameRes = R.string.unique_armours
            iconRes = R.drawable.unique_armours_icon
        }

        val uniqueAccessoriesDrawerItem = SecondaryDrawerItem().apply {
            identifier = 10
            nameRes = R.string.unique_accessories
            iconRes = R.drawable.unique_accessories_icon
        }

        val uniqueFlasksDrawerItem = SecondaryDrawerItem().apply {
            identifier = 11
            nameRes = R.string.unique_flasks
            iconRes = R.drawable.unique_flasks_icon
        }

        val uniqueJewelsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 12
            nameRes = R.string.unique_jewels
            iconRes = R.drawable.unique_jewels_icon
        }

        val skillGemsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 13
            nameRes = R.string.skill_gems
            iconRes = R.drawable.skill_gems_icon
        }

        val clusterJewelsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 14
            nameRes = R.string.cluster_jewels
            iconRes = R.drawable.cluster_jewels_icon
        }

        val atlasSection = SectionDrawerItem().apply {
            identifier = 15
            nameRes = R.string.atlas
        }

        val mapsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 16
            nameRes = R.string.maps
            iconRes = R.drawable.maps_icon
        }

        val blightedMapsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 17
            nameRes = R.string.blighted_maps
            iconRes = R.drawable.blighted_maps_icon
        }

        val blightRavagedMapsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 18
            nameRes = R.string.blight_ravaged_maps
            iconRes = R.drawable.blight_ravaged_maps_icon
        }

        val scourgedMapsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 19
            nameRes = R.string.scourged_maps
            iconRes = R.drawable.scourged_maps_icon
        }

        val uniqueMapsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 20
            nameRes = R.string.unique_maps
            iconRes = R.drawable.unique_maps_icon
        }

        val deliriumOrbsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 21
            nameRes = R.string.delirium_orbs
            iconRes = R.drawable.delirium_orbs_icon
        }

        val invitationsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 22
            nameRes = R.string.invitations
            iconRes = R.drawable.invitations_icon
        }

        val scarabsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 23
            nameRes = R.string.scarabs
            iconRes = R.drawable.scarabs_icon
        }

        val watchstonesDrawerItem = SecondaryDrawerItem().apply {
            identifier = 24
            nameRes = R.string.watchstones
            iconRes = R.drawable.watchstones_icon
        }

        val craftingSection = SectionDrawerItem().apply {
            identifier = 25
            nameRes = R.string.crafting
        }

        val baseTypesDrawerItem = SecondaryDrawerItem().apply {
            identifier = 26
            nameRes = R.string.base_types
            iconRes = R.drawable.base_types_icon
        }

        val fossilsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 27
            nameRes = R.string.fossils
            iconRes = R.drawable.fossils_icon
        }

        val resonatorsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 28
            nameRes = R.string.resonators
            iconRes = R.drawable.resonators_icon
        }

        val helmetEnchantsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 29
            nameRes = R.string.helmet_enchants
            iconRes = R.drawable.helmet_enchants_icon
        }

        val beastsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 30
            nameRes = R.string.beasts
            iconRes = R.drawable.beasts_icon
        }

        val essencesDrawerItem = SecondaryDrawerItem().apply {
            identifier = 31
            nameRes = R.string.essences
            iconRes = R.drawable.essences_icon
        }

        val vialsDrawerItem = SecondaryDrawerItem().apply {
            identifier = 32
            nameRes = R.string.vials
            iconRes = R.drawable.vials_icon
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
    }
}
