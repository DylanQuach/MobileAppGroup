package com.example.drawingapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BrushViewModel : ViewModel(){

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun pickColor(newColor: String){
            _color.value = newColor
    }
    
    fun pickBrush(newBrush: String){
        _brush.value = newBrush
    }
    fun pickBrushSize(newSize: Float){
        _size.value = newSize
        sizeTrue = newSize
    }

    fun doSomething(){

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