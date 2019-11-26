package com.android.diworkshop.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(

  private val feedViewModel: FeedViewModel) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(FeedViewModel::class.java!!)) {
      return feedViewModel as T
    }
    throw IllegalArgumentException("Unknown class name")
  }
}