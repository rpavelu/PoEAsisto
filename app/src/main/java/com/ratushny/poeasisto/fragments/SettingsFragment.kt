package com.ratushny.poeasisto.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.ratushny.poeasisto.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
//        TODO: Dark Theme
        val theme = findPreference<Preference>("theme_preference");
        if (theme != null) {
            if (theme.isEnabled) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}