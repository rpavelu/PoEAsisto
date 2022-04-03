package com.ratushny.poeasisto

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.ratushny.poeasisto.di.RetrofitUrl
import com.ratushny.poeasisto.fragments.SettingsFragment
import timber.log.Timber

class Application : Application(), ApplicationComponentHolder {
    override val daggerComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }

    override val ninjaComponent: NinjaComponent by lazy {
        DaggerNinjaComponent.builder()
            .url(RetrofitUrl("https://poe.ninja/api/data/"))
            .appComponent(daggerComponent)
            .build()
    }

    override val leagueComponent: LeagueComponent by lazy {
        DaggerLeagueComponent.builder()
            .url(RetrofitUrl("http://api.pathofexile.com/"))
            .appComponent(daggerComponent)
            .build()
    }

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

interface ApplicationComponentHolder {
    val daggerComponent: ApplicationComponent
    val ninjaComponent: NinjaComponent
    val leagueComponent: LeagueComponent
}