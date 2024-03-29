package com.example.drawingapp

import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path

class ContinuousDrawing {

    private val points = mutableListOf<Pair<Float, Float>>()

    private var size: Float = 0.0f

    private var brushType = "default"

    private var pointPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 10f
        style = (Paint.Style.STROKE)
        isAntiAlias = true

    }


    public fun setPaintColor(color: String?) {
        pointPaint.color = Color.parseColor(color)
        pointPaint.strokeWidth = 10f
        //pointPaint.isAntiAlias = true
    }

    public fun getPaint(): Paint {
        return pointPaint
    }

    // Add a point (x, y) to the list
    fun addElement(x: Float, y: Float) {
        points.add(x to y)
    }

    // Remove a point (x, y) from the list
    fun removeElement(x: Float, y: Float) {
        val pointToRemove = x to y
        points.remove(pointToRemove)
    }

    fun getPoints(): MutableList<Pair<Float, Float>> {
        return points
    }

    fun getBrushSize(): Float {
        return size
    }

    fun setStrokeWidth(width: Float) {
        pointPaint.strokeWidth = width
    }

    fun pickBrushSize(newSize: Float?) {

        if (newSize != null) {
            size = newSize
        }

        if (newSize != null) {

            pointPaint.strokeWidth = newSize
        }

    }

    public fun getBrushType(): String {
        return brushType
    }

    fun pickBrush(newBrush: String?) {

        if (newBrush != null) {
            brushType = newBrush
        }

        if (newBrush == "Circle") {
            pointPaint.strokeCap = Paint.Cap.ROUND
            pointPaint.strokeJoin = Paint.Join.ROUND
        } else if (newBrush == "Square") {
            pointPaint.strokeCap = Paint.Cap.SQUARE
            pointPaint.strokeJoin = Paint.Join.MITER
        }

    }

}