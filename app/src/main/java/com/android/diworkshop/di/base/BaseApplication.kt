package com.android.diworkshop.di.base

import android.app.Activity
import android.app.Application
import com.android.diworkshop.di.component.DaggerAppComponent
import com.android.diworkshop.di.modules.NetworkModule
import com.android.diworkshop.di.modules.AppModule
import com.android.diworkshop.utils.BASE_URL
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BaseApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(BASE_URL))
            .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}