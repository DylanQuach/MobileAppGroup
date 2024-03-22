package com.example.drawingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapp.databinding.FragmentSizeBinding


class SizeFragment(private var vm: BrushViewModel) : Fragment() {
    //private lateinit var myViewModel : BrushViewModel

    val binding: FragmentSizeBinding by lazy { FragmentSizeBinding.inflate(layoutInflater)}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activityViewModel : BrushViewModel by activityViewModels()
        vm = activityViewModel
        binding.sizeSlider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                vm.pickBrushSize(progress.toFloat())
                binding.sizeNum.text = binding.sizeSlider.progress.toString()
            }
        })

        binding.sizeNum.text = binding.sizeSlider.progress.toString()
        return binding.root
    }

}

