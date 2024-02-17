package com.example.drawingapp

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class BrushViewModel : ViewModel(){

    //Model
    @RequiresApi(Build.VERSION_CODES.O)
    private val _color : MutableLiveData<Color> =
        MutableLiveData(Color.valueOf(1f, 1f, 0f))

    private val _brush : MutableLiveData<String> = MutableLiveData("")

    private val _size : MutableLiveData<Int> = MutableLiveData(1)

    @RequiresApi(Build.VERSION_CODES.O)
    val color  = _color as LiveData<Color>
    public var intColor = 1;

    val brush = _brush as LiveData<String>

    val size = _size as LiveData<Int>

    @RequiresApi(Build.VERSION_CODES.O)
    fun pickColor(){
        with(Random.Default) {
            _color.value = Color.valueOf(nextFloat(), nextFloat(), nextFloat())
        }
    }
    
    fun pickBrush(newBrush: String){
        _brush.value = newBrush
    }
    fun pickBrushSize(newSize: Int){
        _size.value = newSize
    }

    fun doSomething(){

    }

    fun getBrush(): String? {
        return _brush.value
    }

}