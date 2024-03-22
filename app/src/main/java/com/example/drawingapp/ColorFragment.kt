package com.example.drawingapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapp.databinding.FragmentColorBinding


class ColorFragment(private var vm: BrushViewModel) : Fragment() {

    //private lateinit var myViewModel : BrushViewModel

    val binding: FragmentColorBinding by lazy { FragmentColorBinding.inflate(layoutInflater)}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activityViewModel : BrushViewModel by activityViewModels()

        binding.blueButton.setOnClickListener{
            activityViewModel.pickColor("blue")
        }
        binding.redButton.setOnClickListener{
            activityViewModel.pickColor("red")
        }
        binding.greenButton.setOnClickListener{
            activityViewModel.pickColor("green")
        }
        return binding.root
    }


}