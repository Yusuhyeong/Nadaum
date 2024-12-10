package com.bucketlist.nadaum.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bucketlist.nadaum.R
import com.bucketlist.nadaum.databinding.ActivityMainBinding
import com.bucketlist.nadaum.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
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
        // userInfo load
    }
}