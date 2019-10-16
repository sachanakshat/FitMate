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
        maleCheck.isChecked = true
        maleCheck.setOnClickListener(this)
        femaleCheck.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        when(view!!.id){
            maleCheck!!.id -> {femaleCheck.isChecked = false
                                if(!maleCheck.isChecked)
                                    maleCheck.isChecked = true}

            femaleCheck!!.id -> {maleCheck.isChecked = false
                                    if(!femaleCheck.isChecked)
                                        femaleCheck.isChecked = true}
        }
    }
}
