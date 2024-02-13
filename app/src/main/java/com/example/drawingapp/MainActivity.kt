package com.example.drawingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.drawingapp.databinding.ActivityMainBinding

val binding: ActivityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}

//val recycler by lazy{ binding.settingRecycler }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}