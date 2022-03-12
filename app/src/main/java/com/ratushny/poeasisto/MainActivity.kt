package com.ratushny.poeasisto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.withIdentifier
import com.mikepenz.materialdrawer.model.interfaces.withName
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        val itemCurrency = PrimaryDrawerItem().withIdentifier(1).withName("Currency")
        val itemFragment = SecondaryDrawerItem().withIdentifier(2).withName("Fragments")

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
