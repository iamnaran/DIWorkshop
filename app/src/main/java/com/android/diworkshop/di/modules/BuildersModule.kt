package com.android.diworkshop.di.modules

import com.android.diworkshop.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjection




@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity

//    @ContributesAndroidInjector
//    internal abstract fun contributeMyFragment(): MyFragment
//    For Fragments, we initialise our dependencies in onAttach()
//    open fun onAttach(context: Context?) {
//        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//    }
}