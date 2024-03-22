package com.example.drawingapp

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import kotlinx.coroutines.CoroutineScope

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class PngFileRepository(private val cs: CoroutineScope, private val dao: PngFileDao) {

    suspend fun saveImage(
        bitmap: Bitmap?,
        tempName: String,
        filesDir: File,
        application: Application
    ) {

        val fetchedData = PngFile(tempName, filesDir)

        dao.insertPngFile(fetchedData)

        // save file to apps FileDir
        var file = convertBitmapToFile(bitmap, tempName)
        saveFileToInternalStorage(tempName, file, application)
    }

    // Function to save a file in the app's filesDir directory
    fun saveFileToInternalStorage(fileName: String, data: ByteArray, context: Context) {
        // Get the files directory for the app
        val filesDir = context.filesDir

        // Create a File object representing the file to be saved
        val file = File(filesDir, fileName)

        // Create a FileOutputStream to write data to the file
        val fos = FileOutputStream(file)

        // Write the data to the file
        fos.write(data)

        // Close the FileOutputStream
        fos.close()
    }


    fun convertBitmapToFile(bitmap: Bitmap?, fileNameToSave: String): ByteArray {
        val file = File(Environment.getExternalStorageDirectory().toString() + File.separator + fileNameToSave)
        file.createNewFile()
        // Convert bitmap to byte array
        val baos = ByteArrayOutputStream()
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos)
        } // It can be also saved it as JPEG
        val bitmapdata = baos.toByteArray()
        return bitmapdata
    }

}
