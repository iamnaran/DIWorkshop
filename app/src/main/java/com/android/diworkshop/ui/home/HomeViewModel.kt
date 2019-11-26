package com.android.diworkshop.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.diworkshop.data.model.Home
import com.android.diworkshop.repositories.HomeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository) : ViewModel()  {

    var result: MutableLiveData<List<Home>> = MutableLiveData()

    var error: MutableLiveData<String> = MutableLiveData()

    var loader: MutableLiveData<Boolean> = MutableLiveData()

    private lateinit var disposableObserver: DisposableObserver<List<Home>>


    fun onResult(): LiveData<List<Home>> {
        return result
    }

    fun onError(): LiveData<String> {
        return error
    }

    fun onLoader(): LiveData<Boolean> {
        return loader
    }

    fun getFeeds() {

        disposableObserver = object : DisposableObserver<List<Home>>() {
            override fun onComplete() {

            }

            override fun onNext(homeList: List<Home>) {
                result.postValue(homeList)
                loader.postValue(false)
            }

            override fun onError(e: Throwable) {
                error.postValue(e.message)
                loader.postValue(false)
            }
        }

        homeRepository.getFeeds()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)
    }

    fun disposeElements(){
        if(!disposableObserver.isDisposed) disposableObserver.dispose()
    }


}