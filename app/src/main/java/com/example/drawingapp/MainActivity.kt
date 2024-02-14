package com.example.drawingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.drawingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    val recycler by lazy{ binding.settingRecycler }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectFragment = SelectSettingFragment()
        selectFragment.setListener {
            if (it == "color") {
                val selectedFrag = ColorFragment()
                val fTrans = supportFragmentManager.beginTransaction()
                fTrans.replace(R.id.settingFragment, selectedFrag)
                fTrans.commit()
            }
            else if (it == "brush") {
                val selectedFrag = BrushFragment()
                val fTrans = supportFragmentManager.beginTransaction()
                fTrans.replace(R.id.settingFragment, selectedFrag)
                fTrans.commit()
            }
            else if (it == "size") {
                val selectedFrag = SizeFragment()
                val fTrans = supportFragmentManager.beginTransaction()
                fTrans.replace(R.id.settingFragment, selectedFrag)
                fTrans.commit()
            }
        }
        val fTrans = supportFragmentManager.beginTransaction()
        fTrans.replace(R.id.selectSettingFragmentView, selectFragment)
        fTrans.commit()

    }
}