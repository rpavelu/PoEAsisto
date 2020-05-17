package com.ratushny.poeasisto.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.ratushny.poeasisto.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val darkTheme = preferenceManager.sharedPreferences.getBoolean("dark_theme_preference", false)
        if (darkTheme) {
            activity?.setTheme(R.style.DarkAppTheme)
        } else {
            activity?.setTheme(R.style.LightAppTheme)
        }
    }
}