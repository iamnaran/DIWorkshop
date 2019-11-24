package com.android.diworkshop.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable


@Entity(
    tableName = "home"
)
data class Home(

    @Json(name = "id")
    @PrimaryKey
    val id: String,

    @Json(name = "body")
    val name: String?,

    @Json(name = "title")
    val title: String?


) : Serializable


// its a model and acts as an Database Fields