package com.bucketlist.nadaum.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bucketlist.nadaum.R
import com.bucketlist.nadaum.databinding.ActivityLoginBinding
import com.bucketlist.nadaum.databinding.ActivityMainBinding
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

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this

        // userInfo load

        // userInfo putExtra

        // intent to MainActivity
        // 2000 sleep
        // start MainActivity
    }
}