package com.example.drawingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.drawingapp.databinding.ActivityMainBinding

//val binding: ActivityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}

//val recycler by lazy{ binding.settingRecycler }

class MainActivity : AppCompatActivity() {
    companion object
    {
        var hasSeenSplash = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!hasSeenSplash)
        {
            val gotoSplashScreen = Intent(this@MainActivity, CustomSplashScreen::class.java)
            startActivity(gotoSplashScreen)
            hasSeenSplash = true
        }
    }
}