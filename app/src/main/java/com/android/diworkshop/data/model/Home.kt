package com.android.diworkshop.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable


@Entity(
    tableName = "medias"
)
data class Home(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @Json(name = "download_url")
    @ColumnInfo(name = "photos_data")
    var url: String?,

    @Json(name = "author")
    var authorName: String?


) : Serializable


// its a model and acts as an Database Fields