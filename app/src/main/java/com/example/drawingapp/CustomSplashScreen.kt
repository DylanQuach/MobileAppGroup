package com.example.drawingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class CustomSplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val intent = Intent(this@CustomSplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()//prevents the user from backing out to the splash screen again
        },1500)
    }
}