package com.example.fitmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmi_check.*

class bmiCheck : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        val w = window
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_check)
        maleCheck.setOnClickListener(this)
        femaleCheck.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
        when(view!!.id){
            maleCheck!!.id -> femaleCheck.isSelected = false
            femaleCheck!!.id -> maleCheck.isSelected = false
        }
    }
}
