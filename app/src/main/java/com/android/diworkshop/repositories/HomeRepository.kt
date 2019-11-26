package com.android.diworkshop.repositories

import android.util.Log
import com.android.diworkshop.data.apiservice.HomeApiService
import com.android.diworkshop.data.model.Home
import com.android.diworkshop.data.database.HomeDao
import com.android.diworkshop.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

class HomeRepository  @Inject constructor(
    private val homeApiService: HomeApiService,
    private val homeDao: HomeDao,
    private val utils: Utils){


    fun getFeeds(): Observable<List<Home>> {
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<List<Home>>? = null
        if (hasConnection){
            observableFromApi = getHomeDataFromApi()
        }
        val observableFromDb = getHomeDataFromLocal()

        return if (hasConnection) Observable.concatArrayDelayError(observableFromApi, observableFromDb)
        else observableFromDb
    }


    private fun getHomeDataFromApi(): Observable<List<Home>> {

        return homeApiService.getPosts()
            .doOnNext {
                Log.e("Server has", it.size.toString())

                for (item in it) {
                    homeDao.insertSpecificHomeData(item)
                }
            }

    }


    private fun getHomeDataFromLocal(): Observable<List<Home>>{

        return homeDao.getAll()
            .toObservable()
            .doOnNext {
                //Print log it.size :)
                Log.e("Databse has", it.size.toString())
            }

    }

}