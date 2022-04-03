package com.ratushny.poeasisto

import com.ratushny.poeasisto.di.*
import com.ratushny.poeasisto.fragments.SettingsFragment
import com.ratushny.poeasisto.ninja.data.model.di.NinjaListRepositoryModule
import com.ratushny.poeasisto.ninja.data.model.di.NinjaNetworkConverterModule
import com.ratushny.poeasisto.ninja.network.NinjaServiceModule
import com.ratushny.poeasisto.ninja.ui.NinjaFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Component
@Singleton
interface ApplicationComponent

@Component(
    modules = [
        RetrofitModule::class,
        NinjaServiceModule::class,
        NinjaNetworkConverterModule::class,
        NinjaListRepositoryModule::class,
    ], dependencies = [ApplicationComponent::class]
)
@NinjaScope
interface NinjaComponent {
    fun inject(ninjaFragment: NinjaFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(applicationComponent: ApplicationComponent): Builder

        @BindsInstance
        fun url(url: RetrofitUrl): Builder

        fun build(): NinjaComponent
    }
}

@Component(
    modules = [
        RetrofitModule::class,
        LeagueListRepositoryModule::class,
        RetrofitLeagueServiceModule::class,
    ], dependencies = [ApplicationComponent::class]
)
@LeagueScope
interface LeagueComponent {
    fun inject(settingsFragment: SettingsFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(applicationComponent: ApplicationComponent): Builder

        @BindsInstance
        fun url(url: RetrofitUrl): Builder

        fun build(): LeagueComponent
    }
}

@Scope
annotation class NinjaScope

@Scope
annotation class LeagueScope