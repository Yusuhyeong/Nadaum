package com.bucketlist.nadaum

import android.app.Application
import android.content.Context
import android.util.Log
import com.bucketlist.nadaum.utils.Constants
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this.applicationContext, Constants.KAKKO_APP_KEY)
        val keyHash = Utility.getKeyHash(this)
        Log.d("AppKey", keyHash)

        Nadaum.applicationContext = this
        Nadaum.mSharedPreferences = this.getSharedPreferences("nadaumPref", Context.MODE_PRIVATE)
    }
}