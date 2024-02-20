package com.example.drawingapp

import android.graphics.Color
import android.graphics.Paint

class ContinuousDrawing {

    private val points = mutableListOf<Pair<Float, Float>>()
    private val paint = Paint()

    private var pointPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 10f
        paint.isAntiAlias = true
    }

    public fun setPaintColor(color: String?) {
        pointPaint.color = Color.parseColor(color)
        pointPaint.strokeWidth = 10f
        pointPaint.isAntiAlias = true
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

    // Example method to print the points
    fun printPoints() {
        println(points)
    }

}