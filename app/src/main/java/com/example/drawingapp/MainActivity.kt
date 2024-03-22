package com.example.drawingapp

//import com.example.drawingapp.databinding.ActivityMainBinding
//import yuku.ambilwarna.AmbilWarnaDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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


        val vm: BrushViewModel by viewModels{
            WeatherViewModelFactory((application as PngFileApplicationClass))}

        vm.pickBrush("Triangle")


        //val myViewModel : BrushViewModel by viewModels()

        //myViewModel.pickBrush("Triangle")

        findViewById<View>(R.id.colorPreview).setBackgroundColor(this@MainActivity.color)

        // Retrieve the value of hasSeenSplash from SharedPreferences
        val sharedPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val hasSeenSplash = sharedPrefs.getBoolean("hasSeenSplash", false)

        if (!hasSeenSplash) {
            val gotoSplashScreen = Intent(this@MainActivity, CustomSplashScreen::class.java)
            startActivity(gotoSplashScreen)
        }

        // Emi code start

        // danger starts here
        val drawFragment = canvasFragment(vm)
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.drawingFragment, drawFragment, "draw_tag")
        // danger ends here

        transaction.addToBackStack(null)
        transaction.commit()

        // Emi code finish
        val selectFragment = SelectSettingFragment()
        selectFragment.setListener {
            if (it == "color") {
                val selectedFrag = ColorFragment(vm)
                val fTrans = supportFragmentManager.beginTransaction()
                fTrans.replace(R.id.settingFragment, selectedFrag)
                fTrans.commit()
            }
            else if (it == "brush") {
                val selectedFrag = BrushFragment(vm)
                val fTrans = supportFragmentManager.beginTransaction()
                fTrans.replace(R.id.settingFragment, selectedFrag)
                fTrans.commit()
            }
            else if (it == "size") {
                val selectedFrag = SizeFragment(vm)
                val fTrans = supportFragmentManager.beginTransaction()
                fTrans.replace(R.id.settingFragment, selectedFrag)
                fTrans.commit()
            }else if(it=="save"){
                GlobalScope.launch(Dispatchers.Main) {
                    vm.saveDrawing()
                }
            }else if(it == "load"){

            }
        }
        val fTrans = supportFragmentManager.beginTransaction()
        fTrans.replace(R.id.selectSettingFragmentView, selectFragment)
        fTrans.commit()


        colorBtn = findViewById<Button>(R.id.selectColorBtn)

        //color = ContextCompat.getColor(this@MainActivity, com.google.android.material.R.color.design_default_color_primary)

    }

}