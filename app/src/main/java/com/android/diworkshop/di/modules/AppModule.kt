package com.android.diworkshop.di.modules

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.diworkshop.data.database.AppDatabase
import com.android.diworkshop.data.model.HomeDao
import com.android.diworkshop.utils.DATABASE_NAME
import com.android.diworkshop.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val app: Application){
    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                // Change the table name to the correct one
                database.execSQL("ALTER TABLE workshop RENAME TO di_workshop")
            }
        }
    }

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideHomeDatabase(app: Application): AppDatabase = Room.databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME).addMigrations(MIGRATION_1_2).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideHomeDao(
        database: AppDatabase): HomeDao = database.getHomeDao()

//    @Provides
//    @Singleton
//    fun provideCryptocurrenciesViewModelFactory(
//        factory: CryptocurrenciesViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)
}