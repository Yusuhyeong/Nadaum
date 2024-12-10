package com.bucketlist.nadaum.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bucketlist.nadaum.R
import com.bucketlist.nadaum.data.BaseBucketList
import com.bucketlist.nadaum.data.BucketList
import com.bucketlist.nadaum.databinding.ActivityLoginBinding
import com.bucketlist.nadaum.databinding.ActivityMainBinding
import com.bucketlist.nadaum.network.FireStore
import com.bucketlist.nadaum.utils.Constants
import com.bucketlist.nadaum.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

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

//        val firestore = FireStore()
//        val bucketList = listOf(
//            BucketList(todo = "숨쉬기", isTodo = false),
//            BucketList(todo = "밥먹기", isTodo = false),
//            BucketList(todo = "운동하기", isTodo = false)
//        )
//
//        val date = "2024-12-11"
//
//        if (uid != null) {
//            firestore.saveBucketList(uid, date, bucketList) { success ->
//                if (success) {
//                    println("Bucket list for $date saved successfully.")
//                } else {
//                    println("Failed to save bucket list.")
//                }
//            }
//        } else {
//            Log.d("TEST_LOG", "uid is null")
//        }

        // userInfo load
    }
}