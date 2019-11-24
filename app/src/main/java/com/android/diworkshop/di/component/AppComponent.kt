package com.android.diworkshop.di.component

import com.android.diworkshop.di.base.BaseApplication
import com.android.diworkshop.di.modules.BuildersModule
import com.android.diworkshop.di.modules.AppModule
import com.android.diworkshop.di.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, BuildersModule::class, AppModule::class, NetworkModule::class]
)
interface AppComponent {
    fun inject(app: BaseApplication)
}