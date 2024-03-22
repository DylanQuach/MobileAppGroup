package com.example.drawingapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    // store a reference to bitmap in customView

    // setbit map method

    //OK for this demo, but views are created/destroyed during lifecycle events so
    //this should really be stored in a viewmodel!
    private lateinit var bitmap : Bitmap
    private lateinit var bitmapCanvas : Canvas
    private lateinit var paint : Paint

    //width/height are 0 when the constructor is called
    //use the lazy delegated property to initialize it on first access, once the size is set
    private val rect: Rect by lazy { Rect(0, 0, width, height) }

    private val drawingList = ArrayList<ContinuousDrawing>()

    fun setBitMap(){ // pass a reference
        this.bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
        bitmapCanvas = Canvas(bitmap)
        paint = Paint()
    }
    fun getBitMap(): Bitmap {
        return bitmap
    }

    fun getDrawingList(): ArrayList<ContinuousDrawing> {
        return drawingList
    }

    fun addPoint(x: Float, y: Float, id: Int) {
        drawingList[id].addElement(x, y)
        invalidate() // Redraw the view
    }

    fun drawTriangle(canvas: Canvas, paint: Paint?, x: Float, y: Float, width: Int) {
        val halfWidth = width / 2
        val path = Path()
        path.moveTo(x.toFloat(), (y - halfWidth).toFloat()) // Top
        path.lineTo((x - halfWidth).toFloat(), (y + halfWidth).toFloat()) // Bottom left
        path.lineTo((x + halfWidth).toFloat(), (y + halfWidth).toFloat()) // Bottom right
        path.lineTo(x.toFloat(), (y - halfWidth).toFloat()) // Back to Top
        path.close()
        canvas.drawPath(path, paint!!)
    }

    fun drawTriangleWithStroke(canvas: Canvas) {
        for (drawing in drawingList) {

            if (drawing.getBrushType() == "Triangle") {
                val points = drawing.getPoints()
                val paint = drawing.getPaint()

                for (point in points) {
                    val x = point.first
                    val y = point.second
                    drawTriangle(canvas, paint, x, y, 100)
                }
            }
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, null, rect, paint)

        for (drawing in drawingList) {

            if (drawing.getBrushType() == "Triangle") {
                drawTriangleWithStroke(canvas)
            } else {
                // Drawing points at each specified location
                for (drawing in drawingList) {
                    val points = drawing.getPoints()
                    val paint = drawing.getPaint()

                    for (point in points) {
                        val x = point.first
                        val y = point.second
                        canvas.drawPoint(x, y, paint)
                    }
                }
            }

        }

    }

    public fun newDrawing(color: String?, brushSize: Float?, brush: String?) {
        val obj = ContinuousDrawing()
        obj.setPaintColor(color)
        obj.pickBrushSize(brushSize)
        obj.pickBrush(brush)
        // set paint to what user has chosen
        drawingList.add(obj)
    }

    public fun drawPaper() {
        paint.color = Color.WHITE
        bitmapCanvas.drawRect(0f, 0f, bitmap.width.toFloat(), bitmap.height.toFloat(), paint)
    }

}


