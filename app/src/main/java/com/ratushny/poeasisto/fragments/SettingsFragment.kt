package com.ratushny.poeasisto.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.preference.DropDownPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.ratushny.poeasisto.ApplicationComponentHolder
import com.ratushny.poeasisto.R
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var settingsViewModelProvider: Provider<SettingsViewModel>

    private lateinit var viewModel: SettingsViewModel
    private lateinit var prefs: SharedPreferences

    companion object {
        const val DARK_THEME_PREF_KEY = "dark_theme"

        const val LEAGUES_LIST_PREF_KEY = "leagues_list"
        const val PICKED_LEAGUE_PREF_KEY = "picked_league"
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as ApplicationComponentHolder)
            .leagueComponent
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        prefs = context?.getSharedPreferences("settings_prefs", Context.MODE_PRIVATE) ?: return

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return settingsViewModelProvider.get().also { it.getLeagueList() } as T
            }
        })[SettingsViewModel::class.java]

        // Dark theme
        val themeSwitch = findPreference<SwitchPreference>("theme_preference")
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            themeSwitch?.isChecked = true
        }

        themeSwitch?.setOnPreferenceChangeListener { preference, newValue ->
            if (newValue.toString().toBoolean()) {
                prefs.edit().putBoolean(DARK_THEME_PREF_KEY, true).apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Timber.i("Dark theme enabled in settings")
            } else {
                prefs.edit().putBoolean(DARK_THEME_PREF_KEY, false).apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Timber.i("Dark theme disabled in settings")
            }
            true
        }

        // League switch
        updateLeaguesList()
    }

    private fun updateLeaguesList() {
        val leaguePrefList = findPreference<DropDownPreference>("league_list_preference")
        val leaguesListForPrefs = StringBuilder()

        val savedLeagues = prefs.getString(LEAGUES_LIST_PREF_KEY, null)

        if (savedLeagues != null) {
            Timber.i("Loading saved leagues since we have them stored")
            val array: MutableList<String> = savedLeagues.split(",").toMutableList()
            array.removeLast()

            val entries: Array<CharSequence?> = arrayOfNulls(array.size)
            val entriesIndexes: Array<CharSequence?> = arrayOfNulls(array.size)
            array.forEachIndexed { index, s ->
                if (leaguePrefList != null) {
                    entries[index] = array[index]
                    entriesIndexes[index] = index.toString()
                }
            }

            leaguePrefList?.entries = entries
            leaguePrefList?.entryValues = entriesIndexes
        }

        if (prefs.getString(PICKED_LEAGUE_PREF_KEY, null) != null) {
            leaguePrefList?.summary = prefs.getString(PICKED_LEAGUE_PREF_KEY, null)
        }

        viewModel.leagueList.observe(this) {
            val entries: Array<CharSequence?> = arrayOfNulls(it.size)
            val entriesIndexes: Array<CharSequence?> = arrayOfNulls(it.size)

            // TODO: Get rid of SSF leagues
            it.forEachIndexed { index, leagueItem ->
                entries[index] = leagueItem.id
                entriesIndexes[index] = index.toString()

                leaguesListForPrefs.append(leagueItem.id)
                leaguesListForPrefs.append(",")
            }

            if (savedLeagues == null || savedLeagues != leaguesListForPrefs.toString()) {
                Timber.i("Load leagues list for first time")
                leaguePrefList?.entries = entries
                leaguePrefList?.entryValues = entriesIndexes
            }

            prefs.edit().putString(LEAGUES_LIST_PREF_KEY, leaguesListForPrefs.toString()).apply()
        }

        leaguePrefList?.setOnPreferenceChangeListener { preference, newValue ->
            val entries: Array<CharSequence> = leaguePrefList.entries
            preference.summary = entries[newValue.toString().toInt()]

            val leagueName = entries[newValue.toString().toInt()].toString()
            prefs.edit()
                .putString(PICKED_LEAGUE_PREF_KEY, leagueName)
                .apply()

            Timber.i("Picked %s league in dropdown menu in settings", leagueName)

            true
        }
    }
}