package com.ratushny.poeasisto

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.ratushny.poeasisto.fragments.SettingsFragment
import timber.log.Timber

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        // Dark Theme
        val settingsPrefs = getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
        if (settingsPrefs != null && settingsPrefs.getBoolean(
                SettingsFragment.DARK_THEME_PREF_KEY,
                false
            )
        ) {
            Timber.i("Setting dark theme on startup")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            Timber.i("Setting light theme on startup")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}