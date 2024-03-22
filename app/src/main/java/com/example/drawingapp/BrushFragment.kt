package com.example.drawingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapp.databinding.FragmentBrushBinding


class BrushFragment( private var vm: BrushViewModel) : Fragment() {

    //private lateinit var myViewModel : BrushViewModel

    val binding: FragmentBrushBinding by lazy {FragmentBrushBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activityViewModel : BrushViewModel by activityViewModels()
        vm = activityViewModel

        binding.triangleButton.setOnClickListener{
            vm.pickBrush("Triangle")
        }
        binding.squareButton.setOnClickListener{
            vm.pickBrush("Square")
        }
        binding.circleButton.setOnClickListener{
            vm.pickBrush("Circle")
        }


        return binding.root
    }
}