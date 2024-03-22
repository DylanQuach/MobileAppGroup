package com.example.drawingapp

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BrushViewModel( private val application: PngFileApplicationClass) : ViewModel(){
    //class .... (application ...)

    //Model
    @RequiresApi(Build.VERSION_CODES.O)
    private val _color : MutableLiveData<String> =
        MutableLiveData("Red")

    private val _brush : MutableLiveData<String> = MutableLiveData("")

    private val _size : MutableLiveData<Float> = MutableLiveData(1.0f)

    @RequiresApi(Build.VERSION_CODES.O)
    val color  = _color as LiveData<String>
    public var intColor = 1;

    val brush = _brush as LiveData<String>

    val size = _size as LiveData<Float>

    var sizeTrue = 0.0f

    //live data of bitmap

    var bitmap = MutableLiveData<Bitmap>()


    @RequiresApi(Build.VERSION_CODES.O)
    fun pickColor(newColor: String){
            _color.value = newColor
    }


    fun setBitMap(){

    }

    suspend fun saveDrawing(){
        // pass through filename, and dir

        val filesDir = application.filesDir

        //temp filename, get from txt value

        val tempName: String = "Hello, world!"

        application.pngFileRepository.saveImage(bitmap.value, tempName, filesDir, application)
    }

    fun pickBrush(newBrush: String){
        _brush.value = newBrush
    }
    fun pickBrushSize(newSize: Float){
        _size.value = newSize
        sizeTrue = newSize
    }

    fun getBrush(): String? {
        return brush.value
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getColor(): String? {
        return _color.value
    }

    fun getBrushSize(): LiveData<Float> {
        return size
    }

}


// This factory class allows us to define custom constructors for the view model
// class ... (application: PngFileApplicationClaa) : ...
class WeatherViewModelFactory( private val application: PngFileApplicationClass) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrushViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BrushViewModel( application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}