package com.example.drawingapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapp.databinding.FragmentSelectSettingBinding
import yuku.ambilwarna.AmbilWarnaDialog


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
    private var clickCallback : () -> Unit = {}
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
        return binding.root
    }

    public fun setListener(listener: (buttonName: String) -> Unit){
        onclickButton = listener
    }

}