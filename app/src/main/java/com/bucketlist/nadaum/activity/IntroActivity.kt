package com.bucketlist.nadaum.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.bucketlist.nadaum.Nadaum
import com.bucketlist.nadaum.R
import com.bucketlist.nadaum.utils.Constants
import com.bucketlist.nadaum.utils.PersistentKVStore
import com.bucketlist.nadaum.utils.SharedPrefsWrapper

class IntroActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
    private val prefs: PersistentKVStore = SharedPrefsWrapper(Nadaum.mSharedPreferences)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        if (isNetworkConnected()) {
            val userUuid = prefs.getString(Constants.PREF_USER, null)

            if (userUuid.isNullOrEmpty()) { // 사용자 정보 없음
                Log.d(TAG, "No User Info")
                // start LoginActivity
                Handler().postDelayed(Runnable {
                    val loginIntent = Intent(this, LoginActivity::class.java)
                    startActivity(loginIntent)
                    finish()
                }, 2000)
            } else { // 사용자 정보 있음 (자동 로그인)
                Log.d(TAG, "User Info : $userUuid")
                // start MainActivity
                Handler().postDelayed(Runnable {
                    val mainIntent = Intent(this, MainActivity::class.java)
                    mainIntent.putExtra("uid", userUuid)
                    startActivity(mainIntent)
                    finish()
                }, 2000)
            }
        }
    }

    // network 상태 확인
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}