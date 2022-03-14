package com.ratushny.poeasisto

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.google.android.material.elevation.SurfaceColors
import com.mikepenz.materialdrawer.iconics.iconicsIcon
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.descriptionRes
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.model.interfaces.withIdentifier
import com.mikepenz.materialdrawer.model.interfaces.withName
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

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
                itemCurrency -> Toast.makeText(
                    applicationContext,
                    "TODO: Currency fragment",
                    Toast.LENGTH_SHORT
                ).show()
                itemFragment -> Toast.makeText(
                    applicationContext,
                    "TODO: Fragments fragment",
                    Toast.LENGTH_SHORT
                ).show()
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
}
