package com.example.drawingapp

//import com.example.drawingapp.databinding.ActivityMainBinding
//import yuku.ambilwarna.AmbilWarnaDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var color = 1
    lateinit var colorBtn: Button

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

        //color = ContextCompat.getColor(this@MainActivity, com.google.android.material.R.color.design_default_color_primary)

    }

}