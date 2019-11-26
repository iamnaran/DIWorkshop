package com.android.diworkshop.di.modules

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.diworkshop.data.model.Home
import com.android.diworkshop.data.database.HomeDao

@Database(entities = [Home::class], version = 31, exportSchema = true)
abstract class DatabaseModule : RoomDatabase() {
//    exportSchema = false
    // getting instance of HomeDao
    abstract fun getHomeDao(): HomeDao
}


