package com.example.drawingapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapp.databinding.FragmentCanvasBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [canvasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class canvasFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var drawingID = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCanvasBinding.inflate(inflater)

        val viewModel : BrushViewModel by activityViewModels()
        viewModel.color.observe(viewLifecycleOwner){
            binding.customView.drawPaper()
        }

        val my_paint = Paint()
        my_paint.color = Color.RED

        binding.customView.setOnTouchListener { _, event ->
            val x = event.x
            val y = event.y

//            viewModel.getBrush()?.let {
//                binding.customView.setPointPaint(viewModel.getColor().toString(), viewModel.sizeTrue,
//                    it
//                )
//            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {

                    //When the user touches the screen (presses their finger down), the code inside the ACTION_DOWN block is executed.

                    binding.customView.newDrawing(viewModel.getColor(),viewModel.getBrushSize().value, viewModel.getBrush())
                }
                MotionEvent.ACTION_MOVE -> {

                    binding.customView.addPoint(x,y, drawingID)

                }
                MotionEvent.ACTION_UP -> {
                    // it signifies that the user has lifted their finger from the screen

                    drawingID++;

                }

            }
            true // Return true to indicate that you've handled the touch event
        }

        return binding.root

    }


}
