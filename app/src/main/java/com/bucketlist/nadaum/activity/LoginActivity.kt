package com.bucketlist.nadaum.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bucketlist.nadaum.Nadaum
import com.bucketlist.nadaum.databinding.ActivityLoginBinding
import com.bucketlist.nadaum.network.FireStore
import com.bucketlist.nadaum.utils.Constants
import com.bucketlist.nadaum.utils.PersistentKVStore
import com.bucketlist.nadaum.utils.SharedPrefsWrapper
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
    private lateinit var binding: ActivityLoginBinding
    private val prefs: PersistentKVStore = SharedPrefsWrapper(Nadaum.mSharedPreferences)
    private val firestore = FireStore()

    // kakao login callback
    private val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오 계정으로 로그인 실패! " + error.message)
            Log.d(TAG, "로그인 실패")
        } else if (token != null) {
            Log.d(TAG, "카카오 계정으로 로그인 성공! " + token.accessToken)
            Log.d(TAG, "로그인 성공")

            UserApiClient.instance.me { user, error ->
                if (error != null) {
                    Log.e(TAG, "사용자 정보 요청 실패: ${error.message}")
                } else if (user != null) {
                    val userId = user.id.toString()
                    Log.d(TAG, "uid : $userId")

                    firestore.setUid(userId) { success ->
                        if (success) {
                            prefs.putString(Constants.PREF_USER, userId).commit()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("uid", userId)
                            startActivity(intent)
                            finish()
                        } else {
                            Log.e(TAG, "UID 저장 실패")
                        }
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvLoginBtn.setOnClickListener {
            kakaoLoginCheck()
        }

    }

    /**
     * kakao login
     */
    private fun kakaoLoginCheck() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(Nadaum.applicationContext)) { // 카카오톡 app 설치 유무 확인
            UserApiClient.instance.loginWithKakaoTalk(Nadaum.applicationContext) { token, error ->
                if (error != null) {
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    UserApiClient.instance.loginWithKakaoAccount(
                        Nadaum.applicationContext,
                        callback = kakaoCallback
                    )

                } else if (token != null) {
                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            Log.e(TAG, "사용자 정보 요청 실패: ${error.message}")
                        } else if (user != null) {
                            val userId = user.id.toString()
                            Log.d(TAG, "uid : $userId")

                            firestore.setUid(userId) { success ->
                                if (success) {
                                    prefs.putString(Constants.PREF_USER, userId).commit()
                                    val intent = Intent(this, MainActivity::class.java)
                                    intent.putExtra("uid", userId)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Log.e(TAG, "UID 저장 실패")
                                }
                            }
                        }
                    }
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(
                Nadaum.applicationContext,
                callback = kakaoCallback
            )
        }
    }

}