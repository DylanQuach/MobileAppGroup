package com.example.drawingapp

//import com.example.drawingapp.databinding.ActivityMainBinding
//import yuku.ambilwarna.AmbilWarnaDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


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

        //View Model initialization - Dylan
        val myViewModel : BrushViewModel by viewModels()

        myViewModel.pickBrush("Triangle")

        findViewById<View>(R.id.colorPreview).setBackgroundColor(this@MainActivity.color)
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


        colorBtn = findViewById<Button>(R.id.selectColorBtn)

        color = ContextCompat.getColor(this@MainActivity, com.google.android.material.R.color.design_default_color_primary)

        //colorBtn.setOnClickListener{
        //    openColorPicker()
        //}



    }

    /**
     * displays the dialog box containing the color selection tool. upon closing
     * the dialog window the color will be selected and the colorDisplay will
     * be updated
     */
/*    public fun openColorPicker() {

        val ambiListener = object : AmbilWarnaDialog.OnAmbilWarnaListener{
            override fun onCancel(dialog: AmbilWarnaDialog?) {}

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                this@MainActivity.color = color
                findViewById<View>(R.id.brushColorPreview).setBackgroundColor(this@MainActivity.color)
            }
        }
        val warn = AmbilWarnaDialog(this, color, ambiListener)

        warn.show()

    }*/
}