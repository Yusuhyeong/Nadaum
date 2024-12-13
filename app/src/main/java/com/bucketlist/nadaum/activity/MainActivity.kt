package com.bucketlist.nadaum.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bucketlist.nadaum.R
import com.bucketlist.nadaum.databinding.ActivityMainBinding
import com.bucketlist.nadaum.network.FireStore
import com.bucketlist.nadaum.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private val fireStore = FireStore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this

        val uid = intent.getStringExtra("uid")
        // userInfo load

        val date = "2024-12-11"
        val bucketList = mapOf(
            "Go to the gym" to true,
            "Finish project report" to false,
            "Call a friend" to true,
            "Read a book" to false
        )

//        if (uid != null) {
//            fireStore.saveBucketList(uid, date, bucketList) { success ->
//                if (success) {
//                    Log.d(TAG, "저장 성공")
//                } else {
//                    Log.d(TAG, "저장 실패")
//                }
//            }
//        }

//        if (uid != null) {
//            fireStore.getBucketList(uid) { bucketLists ->
//                if (bucketLists != null) {
//                    Log.d(TAG, "====================")
//                    bucketLists.forEach { baseBucketList ->
//                        Log.d(TAG, "date : ${baseBucketList.date}")
//                        Log.d(TAG, "----------------------------------")
//                        baseBucketList.bucketList?.forEach { (todo, isTodo) ->
//                            Log.d(TAG, "todo : $todo, isTodo : $isTodo")
//                        }
//                        Log.d(TAG, "----------------------------------")
//                    }
//                    Log.d(TAG, "====================")
//                } else {
//                    Log.d(TAG, "버킷리스트를 가져오지 못했습니다.")
//                }
//            }
//        }
    }
}