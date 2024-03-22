package com.example.drawingapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.drawingapp.databinding.FragmentSelectSettingBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectSettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectSettingFragment : Fragment() {

    private val binding: FragmentSelectSettingBinding by lazy {FragmentSelectSettingBinding.inflate(layoutInflater)}
    private lateinit var onclickButton : (buttonName: String) -> Unit

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.colorButton.setOnClickListener{
            onclickButton("color")
            //openColorPicker()
        }
        binding.brushButton.setOnClickListener{
            onclickButton("brush")
        }
        binding.sizeButton.setOnClickListener{
            onclickButton("size")
        }

        binding.saveButton.setOnClickListener{
            onclickButton("save")
        }

        binding.loadButton.setOnClickListener{
            onclickButton("load")
        }


        return binding.root
    }

    public fun setListener(listener: (buttonName: String) -> Unit){
        onclickButton = listener
    }

}