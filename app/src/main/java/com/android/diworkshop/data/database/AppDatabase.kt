package com.android.diworkshop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.diworkshop.data.model.Home
import com.android.diworkshop.data.model.HomeDao

@Database(entities = [Home::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // getting instance of HomeDao
    abstract fun getHomeDao(): HomeDao
}


