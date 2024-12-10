package com.bucketlist.nadaum.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bucketlist.nadaum.data.BaseBucketList
import com.bucketlist.nadaum.network.FireStore

class MainViewModel: ViewModel() {
    val fireStore = FireStore()

    private val _user_bucket_list = MutableLiveData<List<BaseBucketList>>()
    val user_bucket_list: LiveData<List<BaseBucketList>>
        get() = _user_bucket_list
}