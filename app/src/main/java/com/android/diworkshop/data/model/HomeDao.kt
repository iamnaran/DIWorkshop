package com.android.diworkshop.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface HomeDao {


//    @Query("SELECT * FROM workshop ORDER BY rank :rank")
//    fun queryData(limit:Int, offset:Int): Single<List<Home>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecificHomeData(home: Home)

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertAllHomeData(home: List<Home>)
}