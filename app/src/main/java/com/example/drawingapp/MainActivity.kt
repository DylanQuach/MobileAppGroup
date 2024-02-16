package com.example.drawingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
//import com.example.drawingapp.databinding.ActivityMainBinding
import yuku.ambilwarna.AmbilWarnaDialog


class MainActivity : AppCompatActivity() {

    //val binding: ActivityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    var color = 1
    lateinit var layout: ConstraintLayout
    lateinit var colorBtn: Button
//    val recycler by lazy{ binding.settingRecycler }

    companion object {
        var hasSeenSplash = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        if(!hasSeenSplash)
        {
            val gotoSplashScreen = Intent(this@MainActivity, CustomSplashScreen::class.java)
            startActivity(gotoSplashScreen)
            hasSeenSplash = true
        }




        // Emi code start
        val drawFragment = canvasFragment()
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.drawingFragment, drawFragment, "draw_tag")
        transaction.addToBackStack(null)
        transaction.commit()

        // Emi code finish

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


        colorBtn = findViewById<Button>(R.id.backButton)
        //layout = findViewById<ConstraintLayout>(R.id.mainLayout)

        color = ContextCompat.getColor(this@MainActivity, com.google.android.material.R.color.design_default_color_primary)

        colorBtn.setOnClickListener{
            openColorPicker()
        }



    }

    /**
     * displays the dialog box containing the color selection tool. upon closing
     * the dialog window the color will be selected and the colorDisplay will
     * be updated
     */
    public fun openColorPicker() {

        val ambiListener = object : AmbilWarnaDialog.OnAmbilWarnaListener{
            override fun onCancel(dialog: AmbilWarnaDialog?) {}

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                this@MainActivity.color = color
                findViewById<View>(R.id.colorButton).setBackgroundColor(this@MainActivity.color)
            }
        }
        val warn = AmbilWarnaDialog(this, color, ambiListener)

        warn.show()

    }
}