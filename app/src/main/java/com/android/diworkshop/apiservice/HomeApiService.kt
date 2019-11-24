package com.android.diworkshop.apiservice

import com.android.diworkshop.data.model.Home
import io.reactivex.Observable
import retrofit2.http.GET

interface HomeApiService {
    @GET("/posts")
    fun getPosts(): Observable<List<Home>>
}