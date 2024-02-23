package com.example.drawingapp
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {


    //OK for this demo, but views are created/destroyed during lifecycle events so
    //this should really be stored in a viewmodel!
    private val bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
    private val bitmapCanvas = Canvas(bitmap)
    private val paint = Paint()

    //width/height are 0 when the constructor is called
    //use the lazy delegated property to initialize it on first access, once the size is set
    private val rect: Rect by lazy {Rect(0,0,width, height)}

    private val drawingList = ArrayList<ContinuousDrawing>()

    fun getDrawingList(): ArrayList<ContinuousDrawing> {
        return drawingList
    }

    fun addPoint(x: Float, y: Float, id: Int){
        drawingList[id].addElement(x,y)
        invalidate() // Redraw the view
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, null, rect, paint)

        // Draw all the points for each ContinuousDrawing
        for (drawing in drawingList) {

            for (point in drawing.getPoints()) {
                val left = point.first - drawing.getBrushSize() / 2
                val top = point.second - drawing.getBrushSize() / 2
                val right = point.first + drawing.getBrushSize() / 2
                val bottom = point.second + drawing.getBrushSize() / 2

                val rect = RectF(left, top, right, bottom)
                canvas.drawRect(rect, paint)
            }

        }


        // Drawing lines between points
        for (drawing in drawingList) {
            val points = drawing.getPoints()
            drawing.setStrokeWidth(drawing.getBrushSize())
            for (i in 0 until points.size - 1) {
                val startPoint = points[i]
                val endPoint = points[i + 1]
                canvas.drawLine(startPoint.first, startPoint.second, endPoint.first, endPoint.second, drawing.getPaint())
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
    public fun drawPaper(){
        paint.color = Color.WHITE
        bitmapCanvas.drawRect(0f,0f, bitmap.width.toFloat(), bitmap.height.toFloat(), paint)
    }

}


