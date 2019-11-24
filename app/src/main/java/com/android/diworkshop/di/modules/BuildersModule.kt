package com.android.diworkshop.di.modules

import com.android.diworkshop.ui.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}