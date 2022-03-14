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
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.ratushny.poeasisto.ninja.DrawerInterface

import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    private lateinit var drawerInterface: DrawerInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val itemCurrency = SecondaryDrawerItem().apply {
            identifier = 1
            nameRes = R.string.currency
        }

        val itemFragment = SecondaryDrawerItem().apply {
            identifier = 2
            nameRes = R.string.fragments
        }

        slider.itemAdapter.add(
            itemCurrency,
            itemFragment
        )
        slider.setSelection(1)
        slider.onDrawerItemClickListener = { v, drawerItem, position ->
            when (drawerItem) {
                itemCurrency -> {
                    drawerInterface.onCurrencyClicked()
                }
                itemFragment -> {
                    drawerInterface.onFragmentClicked()
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
