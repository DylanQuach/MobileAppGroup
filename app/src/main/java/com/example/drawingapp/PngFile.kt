package com.example.drawingapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File

@Entity(tableName = "png_files")
data class PngFile(
    var fileName: String, // Name of the PNG file
    var filePath: File, // Path to the PNG file
) {
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int = 0 // Integer primary key for the database
}

