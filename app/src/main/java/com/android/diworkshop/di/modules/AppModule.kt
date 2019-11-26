package com.android.diworkshop.di.modules

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.diworkshop.data.database.HomeDao
import com.android.diworkshop.ui.home.HomeViewModelFactory
import com.android.diworkshop.utils.DATABASE_NAME
import com.android.diworkshop.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val app: Application){

    // learn about data migration
    // destructive migration and more..

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                // Change the table name to the correct one
                database.execSQL("ALTER TABLE media RENAME TO media_new")
            }
        }
    }

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideHomeDatabase(app: Application): DatabaseModule = Room.databaseBuilder(
        app,
        DatabaseModule::class.java, DATABASE_NAME)
//        .addMigrations(MIGRATION_1_2)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideHomeDao(
        databaseModule: DatabaseModule
    ): HomeDao = databaseModule.getHomeDao()

    @Provides
    @Singleton
    fun provideHomeViewModelFactory(
        homeViewModelFactory: HomeViewModelFactory): ViewModelProvider.Factory = homeViewModelFactory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)
}