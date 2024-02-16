package com.example.drawingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapp.databinding.FragmentBrushBinding


class BrushFragment : Fragment() {

    private lateinit var myViewModel : BrushViewModel

    val binding: FragmentBrushBinding by lazy {FragmentBrushBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activityViewModel : BrushViewModel by activityViewModels()
        myViewModel = activityViewModel

        binding.triangleButton.setOnClickListener{
            myViewModel.pickBrush("Triangle")
        }
        binding.squareButton.setOnClickListener{
            myViewModel.pickBrush("Square")
        }
        binding.circleButton.setOnClickListener{
            myViewModel.pickBrush("Circle")
        }


        return binding.root
    }
}