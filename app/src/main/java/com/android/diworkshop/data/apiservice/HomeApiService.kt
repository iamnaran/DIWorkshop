package com.android.diworkshop.apiservice

import com.android.diworkshop.data.model.Home
import io.reactivex.Observable
import retrofit2.http.GET

interface HomeApiService {
    @GET("/list")
    fun getPosts(): Observable<List<Home>>
}