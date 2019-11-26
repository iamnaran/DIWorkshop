package com.android.diworkshop.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.diworkshop.data.model.Home
import io.reactivex.Single

@Dao
interface HomeDao {


//    @Query("SELECT * FROM workshop ORDER BY rank :rank")
//    fun queryAnyData(id:Int): Single<List<Home>>

    @Query("SELECT * FROM medias")
    fun getAll(): Single<List<Home>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecificHomeData(home: Home)

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertAllHomeData(home: List<Home>)
}