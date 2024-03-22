package com.example.drawingapp

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PngFileApplicationClass : Application() {

    // Coroutine scope tied to the application lifetime, allowing us to run suspend functions
    val scope = CoroutineScope(SupervisorJob())

    // Get a reference to the PNG file database singleton
    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            PngFileDatabase::class.java,
            "png_file_database"
        ).build()
    }

    // Create our repository singleton, using lazy initialization to access the DB when needed
    val pngFileRepository by lazy { PngFileRepository(scope, db.pngFileDao()) }
}
