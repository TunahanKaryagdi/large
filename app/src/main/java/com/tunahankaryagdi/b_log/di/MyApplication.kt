package com.tunahankaryagdi.b_log.di

import android.app.Application
import com.tunahankaryagdi.b_log.data.source.local.AuthDataStore
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class MyApplication @Inject constructor(): Application(){


    private var userId = ""

    fun getUserId(): String{
        return userId
    }

    fun setUserId(userId: String){
        this.userId = userId
    }

}