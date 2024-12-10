package com.bucketlist.nadaum

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this.applicationContext, "1625c91b92367b550db25405ba1d235e")
        val keyHash = Utility.getKeyHash(this)
        Log.d("AppKey", keyHash)
    }
}