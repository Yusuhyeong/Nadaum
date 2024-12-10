package com.bucketlist.nadaum.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bucketlist.nadaum.R
import com.bucketlist.nadaum.databinding.ActivityMainBinding
import com.bucketlist.nadaum.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var bidning: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}