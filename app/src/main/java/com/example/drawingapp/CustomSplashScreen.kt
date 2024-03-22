package com.example.drawingapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class CustomSplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val intent = Intent(this@CustomSplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()//prevents the user from backing out to the splash screen again


            // Store the value of hasSeenSplash in SharedPreferences
            val sharedPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val editor = sharedPrefs.edit()
            editor.putBoolean("hasSeenSplash", true)
            editor.apply()

        },1500)
    }
}