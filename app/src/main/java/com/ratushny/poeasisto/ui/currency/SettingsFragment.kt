package com.ratushny.poeasisto.ui.currency

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.ratushny.poeasisto.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}