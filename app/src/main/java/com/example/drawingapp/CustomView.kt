package com.example.drawingapp
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    //OK for this demo, but views are created/destroyed during lifecycle events so
    //this should really be stored in a viewmodel!
    private val bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
    private val bitmapCanvas = Canvas(bitmap)
    private val paint = Paint()

    //width/height are 0 when the constructor is called
    //use the lazy delegated property to initialize it on first access, once the size is set
    private val rect: Rect by lazy {Rect(0,0,width, height)}


    private val points = mutableListOf<Pair<Float, Float>>()

    // Standalone method to add a point
    fun addPoint(x: Float, y: Float) {
        points.add(Pair(x, y))
        invalidate() // Redraw the view
    }

    private val pointPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 10f
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, null, rect, paint)

        // Draw all the points
        for (point in points) {
            canvas.drawPoint(point.first, point.second, pointPaint)
        }

        // Draw lines between points
        for (i in 0 until points.size - 1) {
            val (startX, startY) = points[i]
            val (endX, endY) = points[i + 1]
            canvas.drawLine(startX, startY, endX, endY, pointPaint)
        }

    }


    public fun drawPaper(){
        paint.color = Color.WHITE
        bitmapCanvas.drawRect(0f,0f, bitmap.width.toFloat(), bitmap.height.toFloat(), paint)
    }



}