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

    @RequiresApi(Build.VERSION_CODES.O)
    val color  = _color as LiveData<Color>

    @RequiresApi(Build.VERSION_CODES.O)
    fun pickColor(){
        with(Random.Default) {
            _color.value = Color.valueOf(nextFloat(), nextFloat(), nextFloat())
        }
    }
    
    fun pickBrush(){

    }
    fun pickBrushSize(){

    }

    fun doSomething(){

    }

}