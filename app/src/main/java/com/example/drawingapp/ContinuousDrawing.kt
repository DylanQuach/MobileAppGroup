package com.example.drawingapp

class ContinuousDrawing {

    private val points = mutableListOf<Pair<Float, Float>>()

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